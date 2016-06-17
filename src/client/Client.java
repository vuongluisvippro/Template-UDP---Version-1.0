package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

@SuppressWarnings("all")
public class Client {
	
	private static DatagramSocket client;
	
	public static void main(String[]args){
		
		boolean contx = true;
		byte[] sendx = new byte[1024];
		byte[] receivex = new byte[1024];
		
		System.out.println("OPEN PROGRAM CLIENT!");
		System.out.print("Nhập host: ");
		String hostname = new Scanner(System.in).nextLine();
		InetAddress host = null;
		try {
			host = InetAddress.getByName(hostname);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		System.out.print("Nhập port: ");
		int port = Integer.parseInt(new Scanner(System.in).nextLine());
		
		do{
			try {
				client = new DatagramSocket();
				System.out.print("Nhập tên chuỗi vắc xin: ");
				String nameVX = new Scanner(System.in).nextLine();
				// GỬI ĐI
				client.send(new DatagramPacket(nameVX.getBytes(), nameVX.getBytes().length,host,port));
				// NHẬN VỀ
				DatagramPacket dp = new DatagramPacket(receivex, receivex.length);
				client.receive(dp);
				String msg = new String(dp.getData()).substring(0,dp.getLength());
				if(msg.charAt(0) == 'a'){
					String msg1 = msg.substring(1,msg.length());
					System.out.println("Số mũi tiêm: "+msg1);
				}else{
					System.out.println(msg);
				}
			} catch (SocketException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.print("tiep tuc khong[c/k] = ");
			String tt = new Scanner(System.in).nextLine();
			if(tt.equals("k")){
				contx = false;
			}
		}while(contx);
	}
}
