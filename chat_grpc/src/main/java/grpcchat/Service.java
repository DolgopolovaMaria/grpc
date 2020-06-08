package grpcchat;

import io.grpc.stub.StreamObserver;

/**
 * Service of grpc
 */
public class Service extends ServiceGrpc.ServiceImplBase {
    private String user1 = null;
    private String user2 = null;
    private StreamObserver<Chat.Message> messagesUser1;
    private StreamObserver<Chat.Message> messagesUser2;

    @Override
    public void connect(Chat.Connect request, StreamObserver<Chat.Message> responseObserver) {
        if (user1 == null){
            user1 = request.getName();
            messagesUser1 = responseObserver;
        }
        else if (user2 == null) {
            user2 = request.getName();
            messagesUser2 = responseObserver;
        }
    }

    @Override
    public void send(Chat.Message request, StreamObserver<Chat.Empty> responseObserver) {
        if (user1 == null || user2 == null){
            return;
        }

        String fromUsername = request.getFrom();
        StreamObserver<Chat.Message> observer;
        if (fromUsername.equals(user1)){
            observer = messagesUser2;
        }
        else{
            observer = messagesUser1;
        }

        observer.onNext(request);
    }
}
