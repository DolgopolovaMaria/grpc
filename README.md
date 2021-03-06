[![Build Status](https://travis-ci.com/DolgopolovaMaria/grpc.svg?branch=master)](https://github.com/DolgopolovaMaria/grpc)

# grpc
:speech_balloon: Simple chat using gRPC. Peer-to-peer connection.

## Example of work

![](https://github.com/DolgopolovaMaria/grpc/blob/master/example/ex.png)

## Build application

To build application for creating jar file:

```bash
cd chat_grpc  
gradle jar
```

To run app from console execute the following commands:

```bash 
cd chat_grpc
gradle jar && java -jar build/libs/chat_grpc-1.0-SNAPSHOT.jar
```

## Build dockerfile and run

You can use the following command for creating docker image:
 
```bash
cd chat_grpc
docker build -t chat .
```

And for run application in client mode you should use net command:

```bash
cd chat_grpc
docker run -i chat
```
As a server:

```bash
cd chat_grpc
docker run -i -p<port>:<port> chat
```

Note: for the server to work correctly, specify the same port everythere!

## Contributors
- Dolgopolova Maria
- Funt Dina
