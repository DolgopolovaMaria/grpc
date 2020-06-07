import io.grpc.Server;
import io.grpc.ServerBuilder;

//  Server for gRPC chat
public class ServerChat {
    private int port;
    private Server server;

    public ServerChat(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new Service()).build();
    }

    public void run() {
        Thread thread = new Thread(() -> {
            try {
                server.start();
                server.awaitTermination();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
