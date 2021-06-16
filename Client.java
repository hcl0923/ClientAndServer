package com.yc.bean04;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
public class Client {
	public static void main(String[] args)
		throws IOException {
		//1.创建套接字对象，绑定IP地址和端口
		Socket socket =new Socket("localhost",10000);
		Scanner sc = new Scanner(System.in);
		
		Scanner clientReader =new Scanner(socket.getInputStream());
		PrintWriter pw =new PrintWriter(socket.getOutputStream());
		
		do{
			System.out.println("请输入您要对服务器说的话: ");
			String line =sc.nextLine();
			pw.println(line);
			pw.flush();
			if("bye".equals(line)) {
				System.out.println("我主动与服务器断开连接....");
				break;
			}
			String response =clientReader.nextLine();
			System.out.println("服务器的回应: "+response);
			if("bye".equals(response)) {
				System.out.println("服务器主动与我断开连接。.. .");
				break;
			}
		} while(true);
		//关闭资源
		socket.close();
		System.out.println("关闭客户端s.....");
	}
}