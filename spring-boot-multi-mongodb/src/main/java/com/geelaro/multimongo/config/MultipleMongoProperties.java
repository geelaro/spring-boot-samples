package com.geelaro.multimongo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultipleMongoProperties {



    @Primary
    @Bean(name = "primaryProperties")
    @ConfigurationProperties(prefix = "mongodb.primary")
    public MongoProperties primaryProperties(){
        return new MongoProperties();
    }


    @Bean(name = "secondaryProperties")
    @ConfigurationProperties(prefix = "mongodb.secondary")
    public MongoProperties secondaryProperties(){
        return new MongoProperties();
    }
}
