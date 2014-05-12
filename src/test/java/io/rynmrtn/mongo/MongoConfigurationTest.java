package io.rynmrtn.mongo;

import io.rynmrtn.mongo.MongoConfiguration;
import org.aeonbits.owner.ConfigFactory;
import org.junit.Assert;
import org.junit.Test;

public class MongoConfigurationTest {

    @Test
    public void validateDefaults() {
        MongoConfiguration configuration = ConfigFactory.create(MongoConfiguration.class);

        validate(configuration);
    }

    public void validate(MongoConfiguration configuration) {
        Assert.assertEquals(configuration.host(), "localhost");
        Assert.assertNull(configuration.username());
        Assert.assertNull(configuration.password());
        Assert.assertEquals(configuration.port(), 27017);
        Assert.assertEquals(configuration.maxConnections(), 10);
        Assert.assertEquals(configuration.connectionTimeout(), 20000);
        Assert.assertEquals(configuration.supportSsl(), false);
        Assert.assertEquals(configuration.useAuthentication(), false);
    }
}
