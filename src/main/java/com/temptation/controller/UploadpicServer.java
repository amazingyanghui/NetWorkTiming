package com.temptation.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
 
public class UploadpicServer {
 
	public static void main(String[] args) throws IOException {
		ServerSocket ss=new ServerSocket(10012);
		Socket s=ss.accept();
		
		System.out.println(s.getInetAddress().getHostAddress()+"connnected.......");
		
		BufferedInputStream burin=new BufferedInputStream(s.getInputStream());
		
		File file=new File("serve.mp3");
		if(!file.exists())
			file.mkdirs();
		PrintStream ps=new PrintStream(new FileOutputStream(file),true);
		
		byte[]buff=new byte[1024];
		int len=-1;
		while((len=burin.read(buff))!=-1) {
			ps.write(buff, 0, len);
		}
		
		PrintStream psout=new PrintStream(s.getOutputStream(),true);
		psout.println("上传成功");
		
		ss.close();
		s.close();
		ps.close();
	}
 
}
