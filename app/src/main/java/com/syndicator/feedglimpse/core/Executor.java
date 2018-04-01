package com.syndicator.feedglimpse.core;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Mahendra on 4/1/2018.
 */

public class Executor extends AsyncTask<Integer, Void, String[]> {

    private ArrayList<String> URLs;
    private Callback callback;

    public Executor(ArrayList<String> URLs, Callback cb) {
        this.URLs = URLs;
        this.callback = cb;
    }

    private String getUrlConnectionResult(java.net.URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        InputStream stream = conn.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);

        int MAX_READ_SIZE = 1000000;
        char[] buffer = new char[MAX_READ_SIZE];
        int readSize;
        StringBuffer buf = new StringBuffer();

        while (((readSize = reader.read(buffer)) != -1) && MAX_READ_SIZE > 0) {
            if (readSize > MAX_READ_SIZE) {
                readSize = MAX_READ_SIZE;
            }
            buf.append(buffer, 0, readSize);
            MAX_READ_SIZE -= readSize;
        }
        return buf.toString();
    }

    @Override
    protected String[] doInBackground(Integer... integers) {
        String[] result = new String[2];

        String[] arrURLs = URLs.toArray(new String[URLs.size()]);

        for (int i = 0; i < arrURLs.length; i++) {
            try {
                URL url = new URL(arrURLs[i] + (integers == null || integers.length == 0 ? "" : "/" + integers[0]));
                result[i] = getUrlConnectionResult(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    @Override
    protected void onPostExecute(String[] s) {
        this.callback.onCallbackCompleted(s);
    }
}
