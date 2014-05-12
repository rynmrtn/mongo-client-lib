import io.rynmrtn.mongo.MongoClientFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import io.rynmrtn.mongo.SharedMongoClient;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Date;

public class UnauthenticatedMongoClientFactoryTest {

    private static String dt;

    @BeforeClass
    public static void beforeClass() {
        MongoClientFactory instance = MongoClientFactory.getInstance();

        createObject(instance.getMongoClient());
    }

    @Test
    public void validateFactoryCreation() {
        SharedMongoClient sharedMongoClient = MongoClientFactory.getInstance().getMongoClient();
        Assert.assertNotNull(sharedMongoClient);
        Assert.assertFalse(sharedMongoClient.isLocked());
    }


    @Test
    public void validateRetrieval() {
        SharedMongoClient sharedMongoClient = MongoClientFactory.getInstance().getMongoClient();
        DB testDb = sharedMongoClient.getDB("test");
        DBCollection intTestCollection = testDb.getCollection("test-" + dt);
        DBObject dbObject = intTestCollection.findOne();

        Assert.assertNotNull(dbObject);
        Assert.assertEquals("mongo-client-lib", dbObject.get("library"));
    }

    private static void createObject(SharedMongoClient mongoClient) {
        dt = (new Date()).toString();

        DB testDb = mongoClient.getDB("test");
        DBCollection collection = testDb.createCollection("test-" + dt, null);

        BasicDBObject basic = new BasicDBObject("name", "test")
                .append("library", "mongo-client-lib")
                .append("date", dt);

        collection.insert(basic);
    }



}
