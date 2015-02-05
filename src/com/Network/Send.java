package com.Network;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import android.util.Log;

public class Send   implements Runnable{
	 private OutputStream   out = null;
	 private DataOutputStream dos = null;
	 private Socket socket=null;
	 private String msg = null;
	 public  Send(Socket socket,String msg){
		 this.socket =socket;
		 this.msg = msg;
		 try {
			 	out=socket.getOutputStream();
				dos = new DataOutputStream(out);
				Log.d("send메서드/전송 초기화","성공");
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 Log.e("send메서드/전송 초기화", "실패");
				 Log.e("sendError", e.toString());
				 
				
				e.printStackTrace();
			}
		
	 }

@Override
public void run() {
	// TODO Auto-generated method stub
	/*전송 */
	try{
	Log.d("msg",msg);
	
	dos.writeBytes(msg);   //byte로 전송
	Log.d("send. / 메시지 전송", "성공");

	}catch(IOException e){
		 Log.e("send / 메시지 전송", "실패");
		 Log.e("send / 메시지 전송", e.toString());
	
	}
	/*전송 끝 */
}
}
