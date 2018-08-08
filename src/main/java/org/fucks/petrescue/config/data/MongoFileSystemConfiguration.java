//package org.fucks.petrescue.config.data;
//
//import com.mongodb.MongoClient;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.mongodb.MongoDbFactory;
//import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
//import org.springframework.data.mongodb.gridfs.GridFsTemplate;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//public class MongoFileSystemConfiguration extends AbstractMongoConfiguration {
//
//    @Value("${mongo.secondary.database}")
//    private String database;
//    @Value("${mongo.secondary.host}")
//    private String host;
//    @Value("${mongo.secondary.uri}")
//    private String uri;
//    @Value("${mongo.secondary.username}")
//    private String username;
//    @Value("${mongo.secondary.password}")
//    private String password;
//    @Value("${mongo.secondary.port}")
//    private int port;
//
//    @Override
//    public MongoClient mongoClient() {
//        return new MongoClient(host, port);
//    }
//
//    @Bean(name = "secondaryMongoTemplate")
//    public MongoTemplate mongoTemplate() throws Exception {
//        return super.mongoTemplate();
//    }
//
//    @Override
//    @Bean
//    @Qualifier("secondaryDbFactory")
//    public MongoDbFactory mongoDbFactory() {
//        return super.mongoDbFactory();
//    }
//
//    @Override
//    @Bean
//    @Qualifier("secondaryDbConverter")
//    public MappingMongoConverter mappingMongoConverter() throws Exception {
//        return super.mappingMongoConverter();
//    }
//
//    @Override
//    protected String getDatabaseName() {
//        return database;
//    }
//
//    @Bean
//    @Qualifier("fileSystemTemplate")
//    public GridFsTemplate fileSystemTemplate(
//            @Qualifier("secondaryDbFactory") MongoDbFactory factory,
//            @Qualifier("secondaryDbConverter") MappingMongoConverter converter
//    ) {
//
//        return new GridFsTemplate(factory, converter);
//    }
//}
