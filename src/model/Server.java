package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {

        try {

            while (!serverSocket.isClosed()) {

                Socket localSocket = serverSocket.accept();
                System.out.println("A new Client has Connected..!");

                ClientHandler clientHandler = new ClientHandler(localSocket);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(5001);
        Server server = new Server(socket);
        server.startServer();
    }
}
