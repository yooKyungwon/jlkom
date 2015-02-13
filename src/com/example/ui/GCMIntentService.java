/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ui;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import static com.example.ui.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.example.ui.CommonUtilities.EXTRA_MESSAGE;
import static com.example.ui.CommonUtilities.SENDER_ID;
import static com.example.ui.CommonUtilities.SERVER_URL;
import static com.example.ui.CommonUtilities.SENDER_ID;
import static com.example.ui.CommonUtilities.displayMessage;

import com.google.android.gcm.GCMBaseIntentService;

/**
 * IntentService responsible for handling GCM messages.
 */
public class GCMIntentService extends GCMBaseIntentService {

    @SuppressWarnings("hiding")
    private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, getString(R.string.gcm_registered,
                registrationId));
        ServerUtilities.register(context, registrationId);
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
    	/*추가 부분 */
    	boolean isAppRunning = false;
    	ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    	  List<RunningTaskInfo> runList = am.getRunningTasks(10);
    	  ComponentName name = runList.get(0).topActivity;
    	  String className = name.getClassName();
       /*추가 부분*/
    	  Log.i(TAG, "Received message. Extras: " + intent.getExtras());
        
        String title = intent.getStringExtra("title");
		String message = intent.getStringExtra("msg");
       // String message = getString(R.string.gcm_message);
        Vibrator vibrator = 
       		 (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
       		vibrator.vibrate(1000);
  
        // notifies user
       // PushWakeLock.acquireCpuWakeLock(context);
        
        /*추가 코드부분*/
       		displayMessage(context, message);
       	
       		Bundle b = new Bundle();
            b.putString("title", title);
            b.putString("msg", message);
            PushWakeLock.acquireCpuWakeLock(context);
       		Intent intent1 = new Intent(context, ShowMsgActivity.class);
       		intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       		intent1.putExtras(b);
       		context.startActivity(intent1);
//        if(className.contains("com.example.ui")) {
//        	isAppRunning = true;
//        	  }
//        if(isAppRunning == true) {
//            displayMessage(context, message);
//        	  } else {
//        		  
//        	   Intent intent2 = new Intent();
//        	   intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        	   intent2.setComponent(new ComponentName("com.example.yyyy", "com.example.yyyy.NotificationActivity"));
//        	      startActivity(intent);
//        	  } 
        /**/
        generateNotification(context, message);
    }

    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);
    }

    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
       displayMessage(context, getString(R.string.gcm_error, errorId)
        		);
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
       displayMessage(context, getString(R.string.gcm_recoverable_error,
               errorId)
                );
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
        int icon = R.drawable.ic_stat_gcm;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }

}
