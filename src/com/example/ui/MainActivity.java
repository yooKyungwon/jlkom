package com.example.ui;

import java.net.Socket;

import com.Network.Connect;
import com.Network.Receive;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements Runnable {
	Socket socket;
	ProgressDialog dialog;
	public static TextView temperature;
	public static TextView humidity;
	 static Receive recv;
	static Connect connect ;
	Button btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09, btn10, btn11, btn12, btn13, btn14;
	static Context context;
	 MainActivity mainActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context =this;
		mainActivity = MainActivity.this;
		temperature = (TextView) findViewById(R.id.temperature);
		humidity = (TextView) findViewById(R.id.humidity);
		
		temperature.setText(Receive.result[1]);
		humidity.setText(Receive.result[0]);
		
		btn01 =(Button)findViewById(R.id.Button01);
		btn02 =(Button)findViewById(R.id.Button02);
		
	
		btn02.setSelected(true);
		
		btn01.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn01.setSelected(true);
				btn02.setSelected(false);
				Log.d("click ", String.valueOf(connect));
				connect.sendMsg("M,01");
			}	
			
		});
		
		
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn01.setSelected(false);
				btn02.setSelected(true);
				connect.sendMsg("02");
			}
		});
		
		
		btn03 =(Button)findViewById(R.id.Button03);
		btn04 =(Button)findViewById(R.id.Button04);
		
		btn04.setSelected(true);
		
		btn03.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn03.setSelected(true);
				btn04.setSelected(false);
			}
		});
		
		
		btn04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn03.setSelected(false);
				btn04.setSelected(true);
			}
		});
		
		btn05 =(Button)findViewById(R.id.Button05);
		btn06 =(Button)findViewById(R.id.Button06);
		
		btn06.setSelected(true);
		
		btn05.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn05.setSelected(true);
				btn06.setSelected(false);
			}
		});
		
		
		btn06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn05.setSelected(false);
				btn06.setSelected(true);
			}
		});
		
		
		btn07 =(Button)findViewById(R.id.Button07);
		btn08 =(Button)findViewById(R.id.Button08);
		
		btn08.setSelected(true);
		
		btn07.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn07.setSelected(true);
				btn08.setSelected(false);
			}
		});
		
		
		btn08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn07.setSelected(false);
				btn08.setSelected(true);
			}
		});
		
		btn09 =(Button)findViewById(R.id.Button09);
		btn10 =(Button)findViewById(R.id.Button10);
		
		btn10.setSelected(true);
		
		btn09.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn09.setSelected(true);
				btn10.setSelected(false);
			}
		});
		
		
		btn10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn09.setSelected(false);
				btn10.setSelected(true);
			}
		});
		
		btn11 =(Button)findViewById(R.id.Button11);
		btn12 =(Button)findViewById(R.id.Button12);
		
		btn12.setSelected(true);
		
		btn11.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn11.setSelected(true);
				btn12.setSelected(false);
			}
		});
		
		
		btn12.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn11.setSelected(false);
				btn12.setSelected(true);
			}
		});
		
		btn13 =(Button)findViewById(R.id.Button13);
		btn14 =(Button)findViewById(R.id.Button14);
		
		btn14.setSelected(true);
		
		btn13.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn13.setSelected(true);
				btn14.setSelected(false);
			}
		});
		
		
		btn14.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn13.setSelected(false);
				btn14.setSelected(true);
			}
		});
		
		
		
		/*thread*/
		try{
			Runnable connectServer = new MainActivity();
			Thread connectThread = new Thread(connectServer);
			connectThread.start();
		}catch(Exception e){
			
		}

	
		
				
		/*Thread end*/
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			return rootView;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
	
		while(true){
	
			
			connect = new Connect();
		if (connect.getSocket()==null){				
				
			// Toast.makeText (this, "소켓 연결 실패", 3).show();
			 connect = new Connect();
		}else{ 		
			
			 recv = new Receive(connect.getSocket(),context,mainActivity);
			  recv.run();
			  break;
			
		}
			
		}
		
		
		 // temperature.setText(recv.getTemperture());
		//	humidity.setText(recv.getHumidity());	
		

	
	}
	
	 public void onBackPressed() {
	      connect.sendMsg("EXIT");
	     
	        finish(); // finish activity

	    }
	
}
