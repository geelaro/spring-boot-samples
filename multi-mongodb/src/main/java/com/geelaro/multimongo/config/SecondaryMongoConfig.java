package com.geelaro.multimongo.config;

import com.mongodb.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.geelaro.multimongo.repository.secondary",
        mongoTemplateRef = SecondaryMongoConfig.MONGO_TEMPLATE)
public class SecondaryMongoConfig {
    protected final static String MONGO_TEMPLATE = "secondaryMongoTemplate";


    @Autowired
    @Qualifier("secondaryProperties")
    private MongoProperties mongoProperties;


    @Bean(name = SecondaryMongoConfig.MONGO_TEMPLATE)
    public MongoTemplate secondaryMongoTemplate() throws Exception {
        return new MongoTemplate(secondaryFactory(this.mongoProperties));
    }

    @Bean
    public MongoDbFactory secondaryFactory(MongoProperties mongo) {
        /**
         * 使用MongoClientURI认证
         * String 格式为：mongodb://user:password/localhost:port/database
         */
        MongoClientURI mongoUri = new MongoClientURI(mongo.getUri());

        return new SimpleMongoDbFactory(new MongoClient(mongoUri), mongo.getDatabase());
    }
}
