import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class ServerChat {
    private int port;
    private Server server;

    public ServerChat(int port) {
        this.port = port;
        this.server = ServerBuilder.forPort(port).addService(new Service()).build();
    }

    void run() {
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
