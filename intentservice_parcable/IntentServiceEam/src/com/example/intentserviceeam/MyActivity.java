package com.example.intentserviceeam;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
@SuppressWarnings("unused")
public class MyActivity extends Activity implements DownloadResultReceiver.Receiver {

  
	private ListView listView = null;

    @SuppressWarnings("rawtypes")
	private ArrayAdapter arrayAdapter = null;

    private DownloadResultReceiver mReceiver;
    String u="http://javatechig.com/api/get_category_posts/?dev=1&slug=android";
    final String url = "http://stg.incentiveweb.com/webservices/TaicoWSController.cfc?method=fnGetCartItems&AuthToken=MTA3NjI6MzU3NjQ3MDUwODEzNDQwOjU5RjIwRDIzLTUwNTYtQTA2My1FNDhENDlGOTFCQjM1RUND&userid=10762&Clientid=5&clientRatio=1.0&clienttaxpercent=30.0";
     ProgressDialog ringProgressDialog;
    TextView textView1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* Allow activity to show indeterminate progressbar */
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

        /* Set activity layout */
        setContentView(R.layout.activity_main);

        /* Initialize listView */
        listView = (ListView) findViewById(R.id.listView);
        textView1 = (TextView) findViewById(R.id.textView1);
        /* Starting Download Service */
        mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);
        Intent intent = new Intent(Intent.ACTION_SYNC, null, this, DownloadService.class);

        /* Send optional extras to Download IntentService */
        intent.putExtra("url", url);
        intent.putExtra("receiver", mReceiver);
        intent.putExtra("requestId", 101);

        startService(intent);
    } 

 
    @SuppressWarnings({ })
	@Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case DownloadService.STATUS_RUNNING:
 
                setProgressBarIndeterminateVisibility(true);
                
                ringProgressDialog = ProgressDialog.show(MyActivity.this, "Please wait ...", "Downloading Image ...", true);
                
                        ringProgressDialog.setCancelable(true);
                break;
            case DownloadService.STATUS_FINISHED:
                /* Hide progress & extract result from bundle */
                 setProgressBarIndeterminateVisibility(true);
                
                 

                String results = resultData.getString("result");
        
                 /* Update ListView with result */
//                arrayAdapter = new ArrayAdapter(MyActivity.this, android.R.layout.simple_list_item_1, results);
//                listView.setAdapter(arrayAdapter);
                textView1.setText(""+results);
                ringProgressDialog.dismiss();

                break;
            case DownloadService.STATUS_ERROR:
                /* Handle the error */
                String error = resultData.getString(Intent.EXTRA_TEXT);
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
                break;
        }
    }
}