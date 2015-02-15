package com.example.ui;

import java.net.Socket;
import static com.example.ui.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.example.ui.CommonUtilities.EXTRA_MESSAGE;
import static com.example.ui.CommonUtilities.SENDER_ID;
import static com.example.ui.CommonUtilities.SERVER_URL;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.Network.Connect;
import com.Network.Receive;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends ActionBarActivity implements Runnable {

	Socket socket;
	ProgressDialog dialog;
	public static TextView temperature;
	public static TextView humidity;
	 static Receive recv;
	static Connect connect ;
	public static Button btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09, btn10, btn11, btn12, btn13, btn14;
	static Context context;
	 static MainActivity mainActivity;
	 
	 	//TextView mDisplay;
	    AsyncTask<Void, Void, Void> mRegisterTask;

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
				btn03.setSelected(true);
				btn04.setSelected(false);
				btn05.setSelected(true);
				btn06.setSelected(false);
				btn07.setSelected(true);
				btn08.setSelected(false);
				btn09.setSelected(true);
				btn10.setSelected(false);
				btn11.setSelected(true);
				btn12.setSelected(false);
				btn13.setSelected(true);
				btn14.setSelected(false);
				
				btn04.setEnabled(false);
				btn06.setEnabled(false);
				btn08.setEnabled(false);
				btn10.setEnabled(false);
				btn12.setEnabled(false);
				btn14.setEnabled(false);
				
				Log.d("click ", String.valueOf(connect));
				connect.sendMsg("01");
			}	
			
		});
		
		
		btn02.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn01.setSelected(false);
				btn02.setSelected(true);
				btn03.setSelected(false);
				btn04.setSelected(true);
				btn05.setSelected(false);
				btn06.setSelected(true);
				btn07.setSelected(false);
				btn08.setSelected(true);
				btn09.setSelected(false);
				btn10.setSelected(true);
				btn11.setSelected(false);
				btn12.setSelected(true);
				btn13.setSelected(false);
				btn14.setSelected(true);
				
				btn04.setEnabled(true);
				btn06.setEnabled(true);
				btn08.setEnabled(true);
				btn10.setEnabled(true);
				btn12.setEnabled(true);
				btn14.setEnabled(true);
				
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
				connect.sendMsg("03");
			}
		});
		
		
		btn04.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn03.setSelected(false);
				btn04.setSelected(true);
				connect.sendMsg("04");
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
				connect.sendMsg("05");
			}
		});
		
		
		btn06.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn05.setSelected(false);
				btn06.setSelected(true);
				connect.sendMsg("06");
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
				connect.sendMsg("07");
			}
		});
		
		
		btn08.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn07.setSelected(false);
				btn08.setSelected(true);
				connect.sendMsg("08");
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
				connect.sendMsg("09");
			}
		});
		
		
		btn10.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn09.setSelected(false);
				btn10.setSelected(true);
				connect.sendMsg("10");
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
				connect.sendMsg("11");
			}
		});
		
		
		btn12.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn11.setSelected(false);
				btn12.setSelected(true);
				connect.sendMsg("12");
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
				connect.sendMsg("13");
			}
		});
		
		
		btn14.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btn13.setSelected(false);
				btn14.setSelected(true);
				connect.sendMsg("14");
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
		
		  checkNotNull(SERVER_URL, "SERVER_URL");
	        checkNotNull(SENDER_ID, "SENDER_ID");
	        // Make sure the device has the proper dependencies.
	        GCMRegistrar.checkDevice(this);
	        // Make sure the manifest was properly set - comment out this line
	        // while developing the app, then uncomment it when it's ready.
	        GCMRegistrar.checkManifest(this);
	        
	        registerReceiver(mHandleMessageReceiver,
	                new IntentFilter(DISPLAY_MESSAGE_ACTION));
	        final String regId = GCMRegistrar.getRegistrationId(this);
	        Log.d("song", "regId["+regId+"]"); 
//	        if (regId.equals("")) {
//	            // Automatically registers application on startup.
//	            GCMRegistrar.register(this, SENDER_ID);
//	        } else {
//	            // Device is already registered on GCM, check server.
//	            if (GCMRegistrar.isRegisteredOnServer(this)) {
//	                // Skips registration.
//	      
//	            } else {
//	                // Try to register again, but not in the UI thread.
//	                // It's also necessary to cancel the thread onDestroy(),
//	                // hence the use of AsyncTask instead of a raw thread.
//	                final Context context = this;
//	                mRegisterTask = new AsyncTask<Void, Void, Void>() {
//
//	                    @Override
//	                    protected Void doInBackground(Void... params) {
//	                        ServerUtilities.register(context, regId);
//	                        return null;
//	                    }
//
//	                    @Override
//	                    protected void onPostExecute(Void result) {
//	                        mRegisterTask = null;
//	                    }
//
//	                };
//	                mRegisterTask.execute(null, null, null);
//	            }
//	        }
		
		
		
		
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
	  @Override
	    protected void onDestroy() {
	        if (mRegisterTask != null) {
	            mRegisterTask.cancel(true);
	        }
	        unregisterReceiver(mHandleMessageReceiver);
	        GCMRegistrar.onDestroy(this);
	        super.onDestroy();
	    }
	  
	 public void onBackPressed() {
	      connect.sendMsg("EXIT");
	     
	        finish(); // finish activity

	    }
	 private void checkNotNull(Object reference, String name) {
	        if (reference == null) {
	            throw new NullPointerException(
	                    getString(R.string.error_config, name)
	            		);
	        }
	    }

	    private final BroadcastReceiver mHandleMessageReceiver =
	            new BroadcastReceiver() {
	        @Override
	        public void onReceive(Context context, Intent intent) {
	            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
	        //    mDisplay.append(newMessage + "\n");
	        }
	    };
}
