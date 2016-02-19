package com.example.app1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button button1=(Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "Data sending...", Toast.LENGTH_LONG).show();
			
			 Intent intent = new Intent();
			   intent.setAction("SOME_ACTION");
			   String key="abc";
			intent.putExtra("key",key);
			   sendBroadcast(intent);
			   
//			   startActivity(new Intent("com.example.app2.MainActivity"));
//			 try
//             {
////                 Intent i = new Intent(Intent.ACTION_MAIN);
////                 PackageManager manager = getPackageManager();
////                 i = manager.getLaunchIntentForPackage("com.example.app2");
//////                 i.putExtra("User_ID", "sush19");
////                 i.addCategory(Intent.CATEGORY_LAUNCHER);
////                 startActivity(i);
//				 
//				 startActivity(new Intent("com.example.app2.MainActivity"));
//             }catch(Exception e)
//             {
//                 e.printStackTrace();
//                 Toast.makeText(v.getContext(), "App Not Found", Toast.LENGTH_LONG).show();
//             }

			}
		});
	}
}
