package grpcchat;

import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static String localHost = "127.0.0.1";

    public static void main(String[] args) {
        ServerChat server;
        Client client;
        Scanner scanner = new Scanner(System.in);
        PrintWriter writer = new PrintWriter(System.out, true);
        String name;
        String address = localHost;
        int port;

        writer.println("For running as server press +. Otherwise enter any other value");
        String input = scanner.next();
        if (input.equals("+")) {
            writer.println("Enter your name and server port");
            name = scanner.next();
            port = scanner.nextInt();
            server = new ServerChat(port);
            server.run();
        }
        else {
            writer.println("Enter your name, server address and port");
            name = scanner.next();
            address = scanner.next();
            port = scanner.nextInt();
        }
        client = new Client(name, address, port);
        chatting(client, scanner);
    }

    private static void chatting(Client client, Scanner scanner){
        client.connect();
        boolean exit = false;
        while (!exit) {
            String text = scanner.nextLine();
            if (text.equals("exit")) {
                exit = true;
                continue;
            }
            client.send(text);
        }
    }
}
