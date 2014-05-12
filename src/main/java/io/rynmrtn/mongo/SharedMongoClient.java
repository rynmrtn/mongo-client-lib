package io.rynmrtn.mongo;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * A wrapper class for {@link MongoClient} designed to simply interaction between Java and MongoDB.
 */
public class SharedMongoClient {

    private final MongoClient mongoClient;
    private final MongoConfiguration mongoConfiguration;

    public SharedMongoClient(MongoClient mongoClient, MongoConfiguration mongoConfiguration) {
        this.mongoClient = mongoClient;
        this.mongoConfiguration = mongoConfiguration;
    }

    /**
     * Retrieves the Mongo database by name. Authentication will be performed if configured.
     *
     * @param dbName The name of the Mongo DB to connect to
     * @return {@link com.mongodb.DB}
     */
    public DB getDB(String dbName) {
        DB db = this.mongoClient.getDB(dbName);

        if(this.mongoConfiguration.useAuthentication())
            db.authenticate(this.mongoConfiguration.username(), mongoConfiguration.password().toCharArray());

        return db;
    }

    /**
     * Informs you if the MongoClient is locked.
     * @return
     */
    public boolean isLocked() {
        return this.mongoClient.isLocked();
    }
}
