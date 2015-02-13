package com.example.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.audiofx.BassBoost.Settings;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class ShowMsgActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
	    super.onCreate(savedInstanceState);
	    Log.d("ShowMsgActivity", "show");
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	   
	    getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
         
        String title, msg;
        Bundle bun = getIntent().getExtras();
        title = bun.getString("title");
        msg = bun.getString("msg");
         
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ShowMsgActivity.this);
         
        alertDialog.setPositiveButton("닫기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PushWakeLock.releaseCpuLock();
                ShowMsgActivity.this.finish();
            }
        });
         
        alertDialog.setNegativeButton("보기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent().setClassName(getPackageName(), getPackageName()+".MainActivity"));
                PushWakeLock.releaseCpuLock();
                ShowMsgActivity.this.finish();
            }
        });
         
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.show();
 
        // 폰 설정의 조명시간을 가져와서 해당 시간만큼만 화면을 켠다.
//        int defTimeOut = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, 15000);
//        TimerTask task = new TimerTask() {
//                 @Override
//                public void run() {
//                       
//                }
//        };
//             
//        Timer timer = new Timer();
//        timer.schedule(task, defTimeOut);
    }
	}

