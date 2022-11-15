import javax.imageio.IIOException;
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
            while(!serverSocket.isClosed()) {

               Socket socket = serverSocket.accept();
                System.out.println("A client is here");
                ClientHandler clientHandler = new ClientHandler(socket);

                 Thread thread = new Thread(clientHandler);
                 thread.start();
            }
        }catch (IOException e) {

        }

    }

    public void closeServerSocket() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Server server = new Server(serverSocket);
        server.startServer();

    }
}