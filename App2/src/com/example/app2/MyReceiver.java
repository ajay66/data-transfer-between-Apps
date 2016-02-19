package com.example.app2;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
//		 Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
	      String key=intent.getStringExtra("key");
	      Constants.data=key;
//	      Toast.makeText(context, "Data Received..."+key, Toast.LENGTH_LONG).show();
	      
//	      context.unregisterReceiver(this);
	      
//	      Intent i=new Intent("com.example.app2.MainActivity");
//	      i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	      i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//	      
//	      context.startActivity(i);
//	      getApplicationContext().startActivity(i);
	      
//	      context.sendBroadcast(new Intent("INTERNET_LOST"));
	      
	      Intent intentone = new Intent(context.getApplicationContext(), MainActivity.class);
	      intentone.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
	      context.startActivity(intentone);
	      
	}
}
