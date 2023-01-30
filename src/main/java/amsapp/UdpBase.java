package amsapp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//import java.net.SocketException;
//import java.util.Scanner;
//import java.net.UnknownHostException;

public class UdpBase {
    public byte[] a;
    public String input;

    public UdpBase() {

    }

    public void javasendudppacket(String input) throws IOException {
        // Scanner sc = new Scanner(System.in);
        // String inp = sc.nextLine();
        this.input = input;
        InetAddress ip;
        DatagramSocket ds = new DatagramSocket();
        ip = InetAddress.getByName("192.168.43.217");
        // ip = InetAddress.getByName("192.168.43.195");
        System.out.println(ip);
        byte buf[] = null;
        buf = input.getBytes();
        // buf = inp.getBytes();
        DatagramPacket data = new DatagramPacket(buf, buf.length, ip, 8000);
        ds.send(data);
        // sc.close();
        ds.close();

    }

    public int javarecieveudppacket() throws IOException {
        DatagramSocket Ds = new DatagramSocket(8888);
        DatagramPacket Dp = null;
        System.out.println("Socket start at port " + 8888);
        byte[] data = new byte[10];
        Dp = new DatagramPacket(data, data.length);
        if (Dp.getLength() != 0) {
            Ds.receive(Dp);
            StringBuilder sb = buildS(data);
            String s = sb.toString();
            int id = Integer.parseInt(s);
            System.out.println("ID " + id + " is captured");
            data = new byte[10];
            Ds.close();
            return id;

        } else {
            System.out.print("packet loading fail");
            Ds.close();
            return 0;
        }

    }

    public StringBuilder buildS(byte[] a) {
        this.a = a;
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }

    public static void main(String args[]) throws IOException {
        // System.out.print("server start");
        // udpBase.javarecieveudppacket();
        // System.out.print("enter id");
        // udpBase.javasendudppacket("1");

    }
}
