package server;

import java.net.SocketException;

public class Server {

    public static void main(String[] args) throws SocketException {
        Thread t = new Thread(new EchoServer());
        t.start();
    }
}
