package com.Network;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Timer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class Connect  implements Runnable{
 private int port = 5000;
 private String ip = new String("175.126.195.162");
 private Socket socket=null;
 private Timer  timer = null;
 private OutputStream  out = null;
 private DataOutputStream dos = null;

 String msg;
 Send send;
 public Connect() {
  try {
   socket = new Socket(ip,port) ;  //서버에 연결
  // timer = new Timer();
   Log.d("connect.class / 소켓 연결", "성공");
  
 
  
	
  }
  catch(Exception e) {
 
   Log.e("connect.class / 소켓 연결", "실패");
   Log.e("connect.class error",e.toString());
 
  }
 
 }
 
 public void sendMsg (String msg){
	 Log.d("sendMsg", msg);
	 this.msg=msg;
	this.run();
	 
//	 try {
//		 send = new Send(socket,msg);
//		 send.run();
//	} catch (Exception e) {
//		// TODO: handle exception
//		Log.e("send t error",e.toString());
//	}
	
 }



@Override
public void run() {
	// TODO Auto-generated method stub
	send = new Send(socket,msg);
	 send.run();
	
}

public Socket getSocket() {
	return socket;
}


} 
