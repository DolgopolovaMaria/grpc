# grpc
:speech_balloon: Simple chat using gRPC. Peer-to-peer connection.

## Example of work

## Build application

To build application for creating jar file:

`
cd chat_grpc
gradle jar
`

To run app from console execute the following commands:

```bash 
cd chat_grpc
gradle jar && java -jar build/libs/gRPC-Chat.jar 
```

## Build dockerfile and run:

You can you the following command for creating dacker image:
 
`
cd chat_grpc
docker build -t chat .
`

And for run application in client mode you should use net command:

`
cd chat_grpc
docker run -i chat
`
As a server:

`
cd chat_grpc
docker run -i -p<port>:<port> chat
`

Note: for the server to work correctly? specify the same port everythere!

## Contributors
- Dolgopolova Maria
- Funt Dina
