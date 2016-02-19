package com.example.parcelable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
@SuppressLint("NewApi") @TargetApi(Build.VERSION_CODES.HONEYCOMB) public class ObjectTranDemo extends Activity implements OnClickListener {  

    private Button sButton,pButton;  
    public  final static String SER_KEY = "com.easyinfogeek.objectPass.ser";  
    public  final static String PAR_KEY = "com.easyinfogeek.objectPass.par";  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);     
        setupViews();  

    }  

    public void setupViews(){  
        sButton = (Button)findViewById(R.id.button1);  
        pButton = (Button)findViewById(R.id.button2);  
        sButton.setOnClickListener(this);  
        pButton.setOnClickListener(this);  
    }  

    public void SerializeMethod(){  
        Person mPerson = new Person();  
        mPerson.setName("Leon");  
        mPerson.setAge(25);  
        Intent mIntent = new Intent(this,ObjectTranDemo1.class);  
        Bundle mBundle = new Bundle();  
        mBundle.putSerializable(SER_KEY,mPerson);  
        mIntent.putExtras(mBundle);  
 
        startActivity(mIntent);  
    }  

    public void PacelableMethod(){  
        Book mBook = new Book();  
        mBook.setBookName("Android Developer Guide");  
        mBook.setAuthor("Leon");  
        mBook.setPublishTime(2014);  
//        Intent mIntent = new Intent(this,ObjectTranDemo2.class);  
        Bundle mBundle = new Bundle();  
        mBundle.putParcelable(PAR_KEY, mBook);  
//        mIntent.putExtras(mBundle);  

//        startActivity(mIntent);  
        
         
        Fragment newContent = new Fragment1();
        newContent.setArguments(mBundle);
		switchFragment(newContent);



    }  

    public void onClick(View v) {  
        if(v == sButton){  
            SerializeMethod();  
        }else{  
            PacelableMethod();  
        }  
    }   
    
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") 
    private void switchFragment(Fragment fragment) {
    	
    	FragmentManager fragmentManager1 = getFragmentManager();
    	fragmentManager1.beginTransaction().replace(R.id.frame_container, fragment)
    		.commit();

    	}
}
