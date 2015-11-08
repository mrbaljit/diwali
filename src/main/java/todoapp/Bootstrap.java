package todoapp;

import com.mongodb.*;

import static spark.Spark.setIpAddress;
import static spark.Spark.setPort;
import static spark.Spark.staticFileLocation;

/**
 * Created by shekhargulati on 09/06/14.
[wsdev01@vmgrp1 tmp]$ ./jre1.8.0_65/bin/java -jar diwali-1.0-SNAPSHOT.jar

 */
public class Bootstrap {
    //private static final String HOST = "vmgrp1";
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws Exception {
        setIpAddress(HOST);
        setPort(PORT);
        staticFileLocation("/public");
        new TodoResource(new TodoService(mongo()));
    }

    private static DB mongo() throws Exception {
        int port = 27017;
        String dbname = "diwali";
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);
        return db;

    }
}
