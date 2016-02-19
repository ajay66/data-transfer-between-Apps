package com.example.app2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView txt2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txt2=(TextView) findViewById(R.id.txt2);
		m1();
	}
	
	public void m1()
	{
		Toast.makeText(MainActivity.this, "Main Activity", 1).show();
		txt2.setText("Data changed to "+Constants.data);
	}
}
