package com.temptation.controller;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
 
public class UploadpicClient {
 
	public static void main(String[] args) throws UnknownHostException, IOException {
		
//		1、建立客户端的Socket服务
		Socket s=new Socket("192.168.1.216",10012);
		
//		2、获取图片资源
		BufferedInputStream burin=
				new BufferedInputStream(new FileInputStream("F:\\CloudMusic\\罗大佑,黄霑,徐克 - 沧海一声笑.mp3"));
		
//		3、获取socket输出流
		PrintStream pso=new PrintStream(s.getOutputStream(),true);
		
//		4、将数据写入到输出流
		byte[]buff=new byte[1024];
		int len=-1;
		while((len=burin.read(buff))!=-1) {
			pso.write(buff, 0, len);
		}
		s.shutdownOutput();
		
//		5、获取服务端的返回的数据
		InputStream is=s.getInputStream();
		byte[]buffin=new byte[1024];
		int lenth=is.read(buffin);
		String str=new String(buffin,0,lenth);
		System.out.println(str);
		
//		6、关闭流
		s.close();
		burin.close();
	}
 
}
