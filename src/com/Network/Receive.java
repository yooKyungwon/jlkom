package com.Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

import com.example.ui.MainActivity;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

public class Receive implements Runnable {
	  private InputStreamReader in = null;
	  private BufferedReader   buffRead = null;
	  private StringBuilder   myCompleteMessage = null;
	  private int     numberOfBytesRead = 0;
	  private char     buff[] = new char[1024];
	  private Socket    m_socket = null;
	  private String temperature ="0";
	  private String humidity = "0";
	  TextView temperature_tv;
	  TextView humidity_tv;
	  Context context;
	  MainActivity mainActivity;
	  static Context mMain;
	  public static String result[] = new String[]{"0","0","0","0","0","0","0"};
	  
	public Receive(Socket socket , Context context,MainActivity mainActivity){
		
		 try {
			    m_socket = socket;          //서버에 접속된 소켓 파일 디스크립터 저장
			    in = new InputStreamReader(m_socket.getInputStream());  //전송 객체 초기화
			    buffRead = new BufferedReader(in);
			    Log.d("receive.class / 데이터 수신 객체 초기화", "성공");
			    Log.d("context",String.valueOf(context));
			    Log.d("mainActivity",String.valueOf(mainActivity));
			    this.mainActivity = mainActivity;
			    this.context= context;
			   } catch(Exception e) {
			    Log.e("receive.class / 데이터 수신 객체 초기화", "실패");
			   }
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		    while(true) {
		     myCompleteMessage = new StringBuilder();    //StringBuilder 객체 초기화
		   
		     if(buff.length > 1024){
		    	 	
		     }else{
		     numberOfBytesRead = buffRead.read(buff, 0, buff.length);//char 배열 buff에 받아들이고 
		                   //받아들인 byte수를 반환
		     myCompleteMessage.append(buff, 0,  numberOfBytesRead); //buff의 데이터를 StringBuilder객체로 이동
		     Log.d("receive.class / 데이터 수신", "성공 : " + myCompleteMessage.toString());//toString메소드를 통해 
		     Receive.result = myCompleteMessage.toString().split(","); 
		                         //String으로 변환
		     Log.d("mesaage",result[0]);
		     Log.d("mesaage1",result[1]);
		     Log.d("mesaage2",result[2]);
		     Log.d("mesaage3",result[3]);
		     mainActivity.runOnUiThread( new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						 MainActivity.humidity.setText(result[1]+"%");
					     MainActivity.temperature.setText(result[2]+"˚C");
					     
					     if(result[3]!= "-1"){
					    	 MainActivity.btn03.setSelected(true);
					    	 MainActivity.btn04.setSelected(false);
					    	 
					     }
					     	
					     else{
					    	 MainActivity.btn03.setSelected(false);
					    	 MainActivity.btn04.setSelected(true);
					     }
					}
				});
		     //Log.d("temperature_tv", String.valueOf(MainActivity.temperature));
	    
		    // MainActivity.humidity.setText(result[0]);
		    // MainActivity.temperature.setText(result[1]);
		     
		    }
		    }
		
		   } catch(Exception e) {
		    Log.e("receive.class / 데이터 수신", "실패");
		    Log.e("receive.class / error",e.toString());
		   }
		  }
	
	public String getTemperature() {
		temperature = result[1];
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		humidity = result[0];
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	}


