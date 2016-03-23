package com.zexus.next.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.zexus.next.xml.XmlInfomation;
import com.zexus.next.xml.XmlParser;

import java.util.ArrayList;
import java.util.List;

public class NextBaseActivity extends ActionBarActivity {
    private final String TAG = "NextBaseActivity";
    private Handler mHandler;
    private NextBaseAdapter mNextBaseAdapter;
    private List<XmlInfomation> mXmlInfomation;

    public Handler getHandler() {
        return  mHandler;
    }

    public NextBaseAdapter getNextBaseAdapter() {
        return mNextBaseAdapter;
    }

    public List<XmlInfomation> getXmlInfomation() {
        return mXmlInfomation;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();
        mXmlInfomation = new ArrayList<>();
        mNextBaseAdapter = new NextBaseAdapter(this, 0, mXmlInfomation);

        XmlParser mXmlParser = new XmlParser(this, "http://www.ithome.com/rss/", mNextBaseAdapter);
        mXmlParser.start();
    }


}
