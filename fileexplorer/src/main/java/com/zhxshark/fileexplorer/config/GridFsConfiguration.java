package com.zhxshark.fileexplorer.config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.logging.Logger;

/**
 * @author zhuxin
 * @date 2019/10/21 19:35
 */
@Configuration
@EnableMongoRepositories
@PropertySource("classpath:application.yml")
public class GridFsConfiguration extends AbstractMongoConfiguration {

    @Autowired
    Environment env;

    @Override
    public MongoClient mongoClient() {
        return new MongoClient(env.getProperty("spring.data.mongodb.host"));
    }

    @Override
    protected String getDatabaseName() {
        return env.getProperty("spring.data.mongodb.database");
    }

    @Bean
    public GridFsTemplate getGridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(),mappingMongoConverter());
    }

}
