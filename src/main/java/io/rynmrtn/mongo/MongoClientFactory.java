package io.rynmrtn.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.aeonbits.owner.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLSocketFactory;
import java.net.UnknownHostException;

/**
 * A factory that aids in the creation of a {@link SharedMongoClient}. The client that
 * gets created is configured by {@link MongoConfiguration}.
 *
 */
public class MongoClientFactory {
    private static Logger logger = LoggerFactory.getLogger(MongoClientFactory.class);

    private static MongoClientFactory instance;
    private SharedMongoClient sharedMongoClient;
    private MongoConfiguration mongoConfiguration;

    public static MongoClientFactory getInstance() {
        if (instance == null)
            initialize();

        return instance;
    }

    private static synchronized void initialize() {
        if(instance == null)
            instance = new MongoClientFactory();
    }

    public synchronized SharedMongoClient getMongoClient() {
        if(sharedMongoClient == null) {
            logger.debug("Bootstrapping a new MongoClient Instance");

            mongoConfiguration = ConfigFactory.create(MongoConfiguration.class);

            MongoClientOptions clientOptions = mongoClientOptionsFromConfiguration();
            ServerAddress serverAddress =  buildAddressFromConfiguration();
            MongoClient mongoClient = new MongoClient(serverAddress, clientOptions);
            sharedMongoClient = new SharedMongoClient(mongoClient, mongoConfiguration);
        }
        return sharedMongoClient;
    }

    private MongoClientOptions mongoClientOptionsFromConfiguration() {
        MongoClientOptions.Builder builder = MongoClientOptions.builder();

        builder.connectionsPerHost(mongoConfiguration.maxConnections());
        builder.connectTimeout(mongoConfiguration.connectionTimeout());

        if(this.mongoConfiguration.supportSsl())
            builder.socketFactory(SSLSocketFactory.getDefault());

        return builder.build();
    }


    private ServerAddress buildAddressFromConfiguration() {
        try {
            return new ServerAddress(mongoConfiguration.host(), mongoConfiguration.port());
        } catch (UnknownHostException hostException) {
            logger.error("Mongo Client unable to create server address with supplied configuration");
            throw new RuntimeException("Cannot connect to Mongo Instance with configured host and port");
        }
    }
}
