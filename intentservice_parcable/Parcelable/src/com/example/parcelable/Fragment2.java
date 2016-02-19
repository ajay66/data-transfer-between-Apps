package com.example.parcelable;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Fragment2 extends Fragment {

	TextView summaryTxt;

	Person person;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_main, container,
				false);

		person = (Person) getArguments()
				.getSerializable(ObjectTranDemo.SER_KEY);

		summaryTxt = (TextView) rootView.findViewById(R.id.ttt);
		summaryTxt.setText(""+person.getAge());

		return rootView;
	}
} 