package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EchoServer extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public EchoServer() throws SocketException {
        socket = new DatagramSocket(4445);
    }

    public void run() {
        running = true;
        System.out.println("Starting Server");
        while (running) {
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            String received
                    = new String(packet.getData(), 0, packet.getLength());

            if (received.contains("end")) {
                running = false;
                continue;
            }
            try {
                System.out.println(received);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Arrays.fill(buf, (byte) 0);
            ;
        }
        socket.close();
    }
}