package com.example.parcelable;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Fragment1 extends Fragment {

	TextView summaryTxt;
	Button pButton;
	Book mBook;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_main, container,
				false);

		mBook = (Book) getArguments().getParcelable(ObjectTranDemo.PAR_KEY);

		summaryTxt = (TextView) rootView.findViewById(R.id.ttt);
		summaryTxt.setText(mBook.getBookName());
		 
		 pButton = (Button)rootView.findViewById(R.id.button1);  
		 pButton.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 Person mPerson = new Person();  
			        mPerson.setName("Leon");  
			        mPerson.setAge(25);  
//			        Intent mIntent = new Intent(this,ObjectTranDemo1.class);  
			        Bundle mBundle = new Bundle();  
			        mBundle.putSerializable(ObjectTranDemo.SER_KEY,mPerson);  
//			        mIntent.putExtras(mBundle);  
			  
//			        startActivity(mIntent);  
			        
			        Fragment newContent = new Fragment2();
			        newContent.setArguments(mBundle);
					switchFragment(newContent);
				
			}
		});

		return rootView;
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB) @SuppressLint("NewApi") 
    private void switchFragment(Fragment fragment) {
    	
    	FragmentManager fragmentManager1 = getFragmentManager();
    	fragmentManager1.beginTransaction().replace(R.id.frame_container, fragment)
    		.commit();

    	}
}