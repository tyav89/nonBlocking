import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    private String address = "127.0.0.1";
    private int port = 52656;
    private InetSocketAddress inetSocketAddress = new InetSocketAddress(address, port);
    private SocketChannel socketChannel;

    public Client() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.connect(inetSocketAddress);
    }

    public void startClient() {
        try (Scanner scanner = new Scanner(System.in)) {
            final ByteBuffer bb = ByteBuffer.allocate(2 << 10);

            String msg;
            System.out.println("Введите строки с пробелами, для завершения end");
            while (true) {

                msg = scanner.nextLine();
                if ("end".equals(msg)) {
                    break;
                }

                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));



                //int byteCount = socketChannel.read(bb);
                //System.out.println(new String(bb.array(), 0, byteCount, StandardCharsets.UTF_8));
                //bb.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
