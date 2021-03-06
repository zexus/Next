package com.zexus.next.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.zexus.next.R;
import com.zexus.next.base.NextBaseActivity;
import com.zexus.next.base.NextBaseAdapter;
import com.zexus.next.database.ItemListSqliteOpenHelper;
import com.zexus.next.flip.FlipViewController;
import com.zexus.next.xml.XmlHandler;
import com.zexus.next.xml.XmlInfomation;
import com.zexus.next.xml.XmlParser;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends NextBaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, XmlHandler.ParseHandlerCallbacks {
    private final String TAG = "NextActivity";
    private String mItemUrl;
    FlipViewController mFlipViewController;
    NextBaseAdapter mNextBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNextBaseAdapter = super.getNextBaseAdapter();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mAlertDialogBuilder = new AlertDialog.Builder(NextActivity.this);
                final View mView = getLayoutInflater().inflate(R.layout.content_new_item, null);
                mAlertDialogBuilder.setView(mView).setTitle("请输入订阅链接").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mItemUrl = ((EditText)mView.findViewById(R.id.content_new_item)).getText().toString();
                        xmlParserWrap(mItemUrl);
                    }
                });

                mAlertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                mAlertDialogBuilder.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ListView mContentList = (ListView)findViewById(android.R.id.list);
        mContentList.setAdapter(super.getNextBaseAdapter());
        mContentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("mXmlTitile", getXmlInfomation().get(i).getXmlTitle());
                bundle.putString("mXmlLink", getXmlInfomation().get(i).getXmlLink());

                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                intent.putExtra("com.zexus.next", bundle);
                startActivity(intent);
            }
        });
        /*mFlipViewController = new FlipViewController(this, FlipViewController.VERTICAL);
        mFlipViewController.setAdapter(super.getNextBaseAdapter());
        setContentView(mFlipViewController);*/
    }

    @Override
    public void notifyUiUpdate() {
        getHandler().post(new updateUI());
    }

    @Override
    public void notifyNewTitle(String title, String imageurl) {
        ItemListSqliteOpenHelper mItemListSqliteOpenHelper = new ItemListSqliteOpenHelper(this);
        if (mItemListSqliteOpenHelper.addItem(title, mItemUrl) != -1) {

        }
    }

    public class updateUI implements Runnable {
        @Override
        public void run() {
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.next, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
        return true;
    }
}
