package org.fucks.petrescue.config.data;

import com.mongodb.AuthenticationMechanism;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
@EnableMongoAuditing
@ComponentScan(basePackages = {"org.fucks.petrescue.entity", "org.fucks.petrescue.service"})
@EnableMongoRepositories(
        basePackages = {"org.fucks.petrescue.repository"})
public class MongoEntitiesConfiguration extends AbstractMongoConfiguration {

    @Value("${mongo.primary.database}")
    private String database;
    @Value("${mongo.primary.host}")
    private String host;
    @Value("${mongo.primary.uri}")
    private String uri;
    @Value("${mongo.primary.username}")
    private String username;
    @Value("${mongo.primary.password}")
    private String password;
    @Value("${mongo.primary.port}")
    private int port;

    @Override
    public MongoClient mongoClient() {
        ServerAddress address = new ServerAddress(this.host, this.port);

        List<MongoCredential> credentials = Arrays.asList(
            MongoCredential.createCredential(username, database, password.toCharArray())
        );

        return new MongoClient(address, credentials);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Bean
    @Qualifier("fileSystemTemplate")
    public GridFsTemplate fileSystemTemplate() throws Exception {

        return new GridFsTemplate(this.mongoDbFactory(), this.mappingMongoConverter());
    }

    @Bean
    public GridFS gridFs() throws Exception {

        return new GridFS(this.mongoClient().getDB(this.database));
    }


//
//    @Primary
//    @Override
//    @Bean
//    @Qualifier("primaryDbClient")
//    public MongoClient mongoClient() {
////        return new MongoClient(
////                Arrays.asList(new ServerAddress(environment.getProperty("db.mongo.host"), 27017)),
////                Arrays.asList(MongoCredential.createCredential(
////                        environment.getProperty("db.mongo.username"),
////                        environment.getProperty("db.mongo.database"),
////                        environment.getProperty("db.mongo.password").toCharArray())));
//
//        return new MongoClient(this.host, this.port);
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return this.database;
//    }
//
//    @Bean
//    public AuditorAware<String> auditorProvider() {
//        return new MongoAuditorAware();
//    }
//
//    @Primary
//    @Bean
//    @Override
//    @Qualifier("primaryMongoTemplate")
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(this.mongoDbFactory());
//    }
//
//    @Bean
//    @Override
//    @Qualifier("primaryDbFactory")
//    public MongoDbFactory mongoDbFactory() {
//        return super.mongoDbFactory();
//    }
//
//    @Bean
//    @Qualifier("fileSystemTemplate")
//    public GridFsTemplate fileSystemTemplate() throws Exception {
//
//        return new GridFsTemplate(this.mongoDbFactory(), this.mappingMongoConverter());
//    }
}
