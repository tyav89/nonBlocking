import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    private ServerSocketChannel serverSocketChannel;
    private String address = "127.0.0.1";
    private int port = 52656;
    private boolean status = true;

    public Server() throws IOException {
        serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(address, port));
    }

    public void severStart() {
        System.out.println("Server on");
        while (status) {
            try (SocketChannel socketChannel = serverSocketChannel.accept()) {
                final ByteBuffer bb = ByteBuffer.allocate(2 << 10);

                while (socketChannel.isConnected()) {
                    int bytesCount = socketChannel.read(bb);

                    if (bytesCount == -1) {
                        break;
                    }



                    final String msg = new String(bb.array(), 0, bytesCount, StandardCharsets.UTF_8).replaceAll("\\s", "");
                    bb.clear();

                    System.out.println(msg);
                    //socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                }
            } catch (IOException e) {
                System.out.println("Server off");
                status = false;
            }
        }
    }
}
