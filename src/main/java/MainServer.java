import java.io.IOException;

public class MainServer {
    public static void main(String[] args) {
        try {
            Server server = new Server();
            server.severStart();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
