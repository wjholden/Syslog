package com.wjholden.woac;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class Syslog {

    public static void main(String[] args) throws UnknownHostException, IOException {
        InetAddress group = InetAddress.getByName("239.5.1.4");
        try (MulticastSocket socket = new MulticastSocket(514)) {
            socket.joinGroup(group);
            byte[] buf = new byte[1500];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            while (true) {
                socket.receive(packet);
                System.out.print(new String(buf, 5, packet.getLength() - 5));
            }
        }
    }   
}
