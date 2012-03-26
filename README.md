# Tapestry 5 - Hornet Connector Integration

This is the Tapestry Integration of [Hornet](https://github.com/nectify/hornet) realtime engine.

[Hornet](https://github.com/nectify/hornet) is a realtime engine that let you enhance your web application by connecting users together. 
Hornet is meant to be used next to your own existing application, no matter what language or framework you're using, by using connectors.

Hornet is powered by NodeJs, Socket.io and is backed by Redis.

See [Hornet](https://github.com/nectify/hornet) README for more information about Hornet and the connector specification


## Connector Installation

Requires:

- redis
- hornet 0.3.x
- Tapestry 5.3.x

Add the following dependency in your pom.xml file:

	<dependency>
		<groupId>com.nectify</groupId>
		<artifactId>hornet-connector-java</artifactId>
		<version>X.Y.Z</version>
	</dependency>


## License

This project is distributed under Apache 2 License. See LICENSE.txt for more information.