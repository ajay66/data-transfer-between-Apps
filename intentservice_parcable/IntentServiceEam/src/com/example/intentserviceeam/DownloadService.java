package com.example.intentserviceeam;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

@SuppressWarnings({ "unused", "deprecation" })
public class DownloadService extends IntentService {

    public static final int STATUS_RUNNING = 0;
    public static final int STATUS_FINISHED = 1;
    public static final int STATUS_ERROR = 2;

    private static final String TAG = "DownloadService";

    public DownloadService() {
        super(DownloadService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
 
        Log.d(TAG, "Service Started!");

        final ResultReceiver receiver = intent.getParcelableExtra("receiver");
        String url = intent.getStringExtra("url");
 
        Bundle bundle = new Bundle();

        if (!TextUtils.isEmpty(url)) {
            /* Update UI: Download Service is Running */
            receiver.send(STATUS_RUNNING, Bundle.EMPTY);
 
            try {
                String results = downloadData(url);

                /* Sending result back to activity */
                if (null != results && results.length() > 0) {
                    bundle.putString("result", results);
                    receiver.send(STATUS_FINISHED, bundle);
                }
            } catch (Exception e) {

                /* Sending error message back to activity */
                bundle.putString(Intent.EXTRA_TEXT, e.toString());
                receiver.send(STATUS_ERROR, bundle);
            }
        }
        Log.d(TAG, "Service Stopping!");
        this.stopSelf();
    }

    private String downloadData(String requestUrl) throws IOException, DownloadException {
    	 
    	String jsonString = null;
		System.out.println("url is" + requestUrl);
		try {
			HttpClient Client = new DefaultHttpClient(); 

			HttpGet httpget = new HttpGet(requestUrl.replace(" ", "%20"));
//			httpget.setHeader("Content-Type", "text/json; charset=utf-8");
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			jsonString = Client.execute(httpget, responseHandler);
			
			
			
 
		} catch (Exception e) {
			e.printStackTrace();
			 throw new DownloadException("Failed to fetch data!!");
		}
				/*try {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);

			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				String re = EntityUtils.toString(entity, HTTP.UTF_8);

				jsonString = re.trim();
			}

		}*/
		return jsonString;
//        InputStream inputStream = null;
//
//        HttpURLConnection urlConnection = null;
//
//        /* forming th java.net.URL object */
//        URL url = new URL(requestUrl);
// 
//        urlConnection = (HttpURLConnection) url.openConnection();
//
//        /* optional request header */
//        urlConnection.setRequestProperty("Content-Type", "application/json");
//
//        /* optional request header */
//        urlConnection.setRequestProperty("Accept", "application/json");
//
//        /* for Get request */
//        urlConnection.setRequestMethod("GET");
//
//        int statusCode = urlConnection.getResponseCode();
//
//        /* 200 represents HTTP OK */
//        if (statusCode == 200) {
//            inputStream = new BufferedInputStream(urlConnection.getInputStream());
//
//            String response = convertInputStreamToString(inputStream);
//
//            String[] results = parseResult(response);
//
//            return results;
//        } else {
//            throw new DownloadException("Failed to fetch data!!");
//        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";

        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

            /* Close Stream */
        if (null != inputStream) {
            inputStream.close();
        }

        return result;
    }

    private String[] parseResult(String result) {

        String[] blogTitles = null;
        try {
            JSONObject response = new JSONObject(result);

            JSONArray posts = response.optJSONArray("posts");

            blogTitles = new String[posts.length()];

            for (int i = 0; i < posts.length(); i++) {
                JSONObject post = posts.optJSONObject(i);
                String title = post.optString("title");

                blogTitles[i] = title;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return blogTitles;
    }

    @SuppressWarnings("serial")
	public class DownloadException extends Exception {

        public DownloadException(String message) {
            super(message);
        }

        public DownloadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}