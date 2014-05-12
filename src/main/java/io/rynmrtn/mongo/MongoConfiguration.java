package io.rynmrtn.mongo;

import org.aeonbits.owner.Config;

/**
 * A Owner configuration interface that is used for configuration of the
 * {@link MongoClientFactory}.
 *
 * This configuration can be overridden on the classpath.
 */
@Config.Sources({
    "classpath:mongo.properties"
})
public interface MongoConfiguration extends Config {

    /**
     * A property specifying the hostname to use to connect to via {@link com.mongodb.MongoClient}.
     *
     * This can be configured with `mongo.host` property. Defaults to localhost.
     *
     * @return int
     */
    @Key("mongodb.host")
    @DefaultValue("localhost")
    String host();

    /**
     * A property specifying the port to use to connect to via {@link com.mongodb.MongoClient}.
     *
     * This can be configured with `mongo.port` property. Defaults to 27017.
     *
     * @return int
     */
    @Key("mongodb.port")
    @DefaultValue("27017")
    int port();

    /**
     * A property specifying the password to use for the authentication of the {@link com.mongodb.DB}.
     *
     * This can be configured with `mongo.username` property. Defaults to null.
     *
     * @return String
     */
    @Key("mongodb.username")
    String username();

    /**
     * A property specifying the password to use for the authentication of the {@link com.mongodb.DB}.
     *
     * This can be configured with `mongo.password` property. Defaults to null.
     *
     * @return String
     */
    @Key("mongodb.password")
    String password();

    /**
     * A property specifying the maximum number of connections for the {@link com.mongodb.MongoClient}.
     *
     * This can be configured with `mongo.maxConnections` property. Defaults to 10.
     * @return int
     */
    @Key("mongodb.maxConnections")
    @DefaultValue("10")
    int maxConnections();

    /**
     * A property specifying the connection timeout for the {@link com.mongodb.MongoClient}.
     *
     * This can be configured with `mongo.connectionTimeout` property. Defaults to 20,000
     * @return int
     */
    @Key("mongodb.connectionTimeout")
    @DefaultValue("20000")
    int connectionTimeout();

    /**
     * A property to indicate if the {@link com.mongodb.MongoClient} to use an SSL
     * connection. If true, the MongoSocket will be configured with
     * a {@link javax.net.ssl.SSLSocketFactory}
     *
     * This can be configured with `mongo.supportSsl` property. Defaults to false.
     *
     * @return boolean
     */
    @Key("mongodb.supportSsl")
    @DefaultValue("false")
    boolean supportSsl();

    /**
     * Helper field to determine whether you need to authenticate your
     * {@link com.mongodb.DB} object.
     *
     * This can be configured with `mongo.useAuthentication` property. Defaults to false.
     *
     * @return boolean
     */
    @Key("mongodb.useAuthentication")
    @DefaultValue("false")
    boolean useAuthentication();
}
