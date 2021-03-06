package com.geelaro.multijpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactorySecondary",
    transactionManagerRef = "transactionManagerSecondary",
    basePackages = {"com.geelaro.multijpa.domain.msg"} ) //Repository地址
public class SecondaryConfig {

    @Autowired @Qualifier("secondaryDataSource")
    private DataSource secondDataSource;

    @Bean("entityPrimaryManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder){
        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
    }

    @Bean(name = "entityManagerFactorySecondary")
    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder){

        return builder
            .dataSource(secondDataSource)
            .properties(getVendorProperties())
            .persistenceUnit("secondPersistenceUnit")
            .packages("com.geelaro.multijpa.domain.msg")
            .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String,String> getVendorProperties(){
        return jpaProperties.getProperties();
    }

    @Bean("transactionManagerSecondary")
    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder){

        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
    }

}
