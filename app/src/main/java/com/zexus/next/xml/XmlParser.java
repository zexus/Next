package com.zexus.next.xml;

import android.content.Context;

import com.zexus.next.base.NextBaseActivity;
import com.zexus.next.base.NextBaseAdapter;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlParser extends Thread {
    private final String TAG = "RSSParser";
    private NextBaseActivity mNextBaseActivity;
    private String mRssUrl = null;
    private NextBaseAdapter mNextBaseAdapter;


    public XmlParser(NextBaseActivity activity, String mRSSURL, NextBaseAdapter nextBaseAdapter) {
        mNextBaseActivity = activity;
        mRssUrl = mRSSURL;
        mNextBaseAdapter = nextBaseAdapter;
    }

    @Override
    public void run() {
        try {
            URLConnection mURLConnection = new URL(mRssUrl).openConnection();
            mURLConnection.setConnectTimeout(10000);
            mURLConnection.connect();
            InputStream mInputStream = mURLConnection.getInputStream();
            parse(mInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parse(InputStream inputStream) {
        try {
            SAXParser mSAXParser = SAXParserFactory.newInstance().newSAXParser();
            mSAXParser.parse(inputStream, new XmlHandler(mNextBaseActivity, mNextBaseAdapter));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
