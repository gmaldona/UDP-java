package client;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client");
        EchoClient c = new EchoClient();
        System.out.println(c.sendEcho("I DONT LIKE UDP"));
        c.close();
    }
}
