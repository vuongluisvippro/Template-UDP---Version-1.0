package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.Scanner;

@SuppressWarnings("all")
public class Server {
	
	private static DatagramSocket server;
	
	public static void main(String[]args){
		
		System.out.println("OPEN PROGRAM SERVER!");
		byte[] sendx = new byte[1024];
		byte[] receivex = new byte[1024];
		System.out.print("Nhập cổng port server: ");
		
		int port = Integer.parseInt(new Scanner(System.in).nextLine());
		try {
			server = new DatagramSocket(port);
			System.out.println("Server already!");
			while(true){
				// NHẬN TỪ CLIENT
				DatagramPacket dp = new DatagramPacket(receivex, receivex.length);
				server.receive(dp);
				String nameVX = new String(dp.getData()).substring(0,dp.getLength());
				System.out.println("Thông điệp từ client: "+nameVX);
				if(new Handle().checkVX(nameVX) > 0){
					String msg = String.valueOf(new Handle().checkVX(nameVX));
					String msg1 = "a"+msg;
					server.send(new DatagramPacket(msg1.getBytes(), msg1.getBytes().length,dp.getAddress(),dp.getPort()));
				}else{
					String msg2 = "Không có Văcxin này!";
					server.send(new DatagramPacket(msg2.getBytes(), msg2.getBytes().length,dp.getAddress(),dp.getPort()));
				}
			}
		} catch (SocketException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
