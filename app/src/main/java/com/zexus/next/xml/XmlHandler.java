package com.zexus.next.xml;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.zexus.next.base.NextBaseActivity;
import com.zexus.next.base.NextBaseAdapter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlHandler extends DefaultHandler {
    private final String TAG = "XmlHandler";
    private boolean mInItemLabel = false;
    private String mXMLTitile;
    private String mXMLLink;
    private String mXMLDescription;
    private StringBuffer mStringBuffer;
    private NextBaseActivity mNextBaseActivity;
    private NextBaseAdapter mNextBaseAdapter;
    private ParseHandlerCallbacks mParseHandlerCallbacks;

    public XmlHandler(NextBaseActivity activity, NextBaseAdapter nextBaseAdapter) {
        mNextBaseActivity = activity;
        mNextBaseAdapter = nextBaseAdapter;
        mStringBuffer = new StringBuffer();
        mParseHandlerCallbacks = (ParseHandlerCallbacks)mNextBaseActivity;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (mStringBuffer != null) {
            mStringBuffer.delete(0, mStringBuffer.length());
        }

        if (localName.equals("item")) {
            mInItemLabel = true;
            mXMLTitile = mXMLLink = mXMLDescription = "";
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (mInItemLabel) {
            if (localName.equals("title")) {
                mXMLTitile = mStringBuffer.toString();
            } else if (localName.equals("link")) {
                mXMLLink = mStringBuffer.toString();
            } else if (localName.equals("description")) {
                mXMLDescription = mStringBuffer.toString();
            } else if (localName.equals("item")) {
                mInItemLabel = false;
                XmlInfomation mXmlInfomation = new XmlInfomation(mXMLTitile, mXMLLink, mXMLDescription);
                mNextBaseActivity.getHandler().post(new elementBringUp(mXmlInfomation));
                if (mParseHandlerCallbacks != null) {
                    mParseHandlerCallbacks.notifyUiUpdate();
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        mStringBuffer.append(ch, start, length);
    }

    public class elementBringUp implements Runnable {
        private XmlInfomation mXmlInfomation;

        public elementBringUp(XmlInfomation xmlInfomation) {
            mXmlInfomation = xmlInfomation;
        }

        @Override
        public void run() {
            mNextBaseAdapter.add(mXmlInfomation);
        }
    }

    public interface ParseHandlerCallbacks {
        void notifyUiUpdate();
    }
}
