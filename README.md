# mongo-client-lib

The default Mongo libraries for Java are annoying to work with, especially if you need to configure SSL or authenticate using a set of credentials. The default Mongo library also does not provide a simple way to constrain your connections

This library is designed to simplify your interaction with a Mongo instance.

## Usage

Using the library is quite simple. `SharedMongoClient` is a basic wrapper around the default `com.mongodb.MongoClient` that simplifies your access to `getDB(String db)`.

	// Create an instance of the factory. This will use the properties from `MongoConfiguration`
	MongoClientFactory instance = MongoClientFactory.getInstance(); // 

	SharedMongoInstance sharedMongoInstance = instance.getMongoClient();
	com.mongodb.DB db = sharedMongoInstance.getDB("mongodb"); // automatically authenticates if configured to do so
	db.getCollection("collection");

## Configuration

It all starts with `MongoConfiguration`, which utilizes the impressive [OWNER](http://owner.aeonbits.org/) library to setup your configuration with a simple properties file.

By default, the following values are configured:

	mongodb.host=localhost
	mongodb.port=27017
	mongodb.useAuthentication=false
	mongodb.supportSsl=false
	mongodb.username= #blank
	mongodb.password= #blank
	mongodb.maxConnections=10
	mongodb.connectionTimeout=20000

Add `mongo.properties` to your classpath to provide your own value overrides.

## Contributing

All suggestions and contributions are welcome. I wrote this library to scratch my own itch and know that it does not currently meet everyone's needs. If you submit a pull request I'll be happy to review it and incorporate it into future versions.

