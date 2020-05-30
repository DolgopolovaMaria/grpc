import io.grpc.stub.StreamObserver;

public class Service extends ServiceGrpc.ServiceImplBase {
    private String user1 = null;
    private String user2 = null;
    StreamObserver<Chat.Message> messages_user1;
    StreamObserver<Chat.Message> messages_user2;

    @Override
    public void connect(Chat.Connect request, StreamObserver<Chat.Message> responseObserver) {
        if (user1 == null){
            user1 = request.getName();
            messages_user1 = responseObserver;
        }
        else if (user2 == null) {
            user2 = request.getName();
            messages_user2 = responseObserver;
        }
    }

    @Override
    public void send(Chat.Message request, StreamObserver<Chat.Empty> responseObserver) {
        if (user1 == null || user2 == null){
            return;
        }
        String from_username = request.getFrom();
        StreamObserver<Chat.Message> observer;
        if (from_username.equals(user1)){
            observer = messages_user2;
        }
        else{
            observer = messages_user1;
        }
        observer.onNext(request);
    }
}
