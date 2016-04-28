package com.zexus.next.ui.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zexus.next.R;
import com.zexus.next.base.NextBaseActivity;

public class WebViewActivity extends NextBaseActivity {
    String mXmlTitile = null;
    String mXmlLink = null;
    WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle mBundle = intent.getBundleExtra("com.zexus.next");
            mXmlTitile = mBundle.getString("mXmlTitile");
            mXmlLink = mBundle.getString("mXmlLink");
            actionBar.setTitle(mXmlTitile);
        }
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(mXmlLink);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == 1000) {
            return new AlertDialog.Builder(this).setItems(R.array.webTextSize, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    switch (whichButton) {
                        case 0:
                            mWebView.getSettings().setTextZoom(70);
                            break;
                        case 1:
                            mWebView.getSettings().setTextZoom(100);
                            break;
                        case 2:
                            mWebView.getSettings().setTextZoom(130);

                    }
                }
            }).create();
        }

        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TITLE, mXmlTitile);
                shareIntent.putExtra(Intent.EXTRA_TEXT, mXmlLink);
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
                break;
            case R.id.action_textsize:
                showDialog(1000);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
