import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.text.SimpleDateFormat;
import java.util.Date;

//  Client for gRPC chat
public class Client {
    private String address;
    private int port;
    private String name;
    private ServiceGrpc.ServiceStub asyncStub;
    private ServiceGrpc.ServiceBlockingStub blockingStub;
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public Client(String whoami, String address, int port) {
        this.name = whoami;
        this.address = address;
        this.port = port;
    }

    public void connect() {
        var channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        asyncStub = ServiceGrpc.newStub(channel);
        blockingStub = ServiceGrpc.newBlockingStub(channel);
        Chat.Connect connect = Chat.Connect.newBuilder().setName(name).build();
        asyncStub.connect(connect, new StreamObserver<Chat.Message>() {
            @Override
            public void onNext(Chat.Message value) {
                System.out.print(value.getFrom());
                System.out.print(" [" + value.getTime() + "]: ");
                System.out.println(value.getText());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }

    public void send(String text) {
        asyncStub.send(Chat.Message.newBuilder()
                .setFrom(name)
                .setTime(format.format(new Date()))
                .setText(text)
                .build(), new StreamObserver<>() {
            @Override
            public void onNext(Chat.Empty value) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {

            }
        });
    }
}
