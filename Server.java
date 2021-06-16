package com.yc.bean04;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
/**
 * 服务器
 * @param args
 * @throws IOException
 */

public class Server {
	public static void main (String[] args) throws IOException {
		ThreadPoolManager manager=new ThreadPoolManager(5);
		
		ServerSocket ss =new ServerSocket(10000);
		System.out.println("服务器启动，监听。。。"+ss.getLocalPort()+"端口");
		while(true){
			Socket s=ss.accept();
			TalkTask t=new TalkTask(s);
			manager.process(t);
		}
	}
}
class TalkTask implements Runnable{
	private Socket s;
	private Scanner sc = new Scanner(System.in);
	public TalkTask(Socket s){
		this.s =s;
	}
	@Override
	public void run(){
		System.out.println("客户端"+s.getInetAddress()+"连接上服务器");
		try {
			Scanner clientReader = new Scanner(s.getInputStream());
			PrintWriter pw =new PrintWriter(s.getOutputStream());
			do{
				String response =clientReader.nextLine();
				System.out.println("客户端"+s.getInetAddress()+"向服务器说: "+response);
				if ("bye".equals(response)){
					System.out.println ("客户端"+s.getInetAddress()+"主动断开与服务器端的连接....");
					break;
				}
				System.out.println("请输入服务器相对客户端说的话: ");
				String line=sc.nextLine();
				pw.println(line);
				pw.flush();
				if ("bye".equals(line)){
					System.out.println("服务器强制与客户端"+s.getInetAddress()+"掉线，，，，");
					break;
				}
			}while(true);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}