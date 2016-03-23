package com.zexus.next.base;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zexus.next.R;
import com.zexus.next.flip.FlipViewController;
import com.zexus.next.xml.XmlInfomation;

import java.util.ArrayList;
import java.util.List;

public class NextBaseAdapter extends ArrayAdapter<XmlInfomation> {
    List<XmlInfomation> mXmlInfomation;
    ViewHolder mViewHolder;
    Context mContext;

    public NextBaseAdapter(Context context, int resource, List<XmlInfomation> objects) {
        super(context, resource, objects);
        mContext = context;
        mXmlInfomation = objects;
    }

    @Override
    public int getCount() {
        return mXmlInfomation.size();
    }

    @Override
    public XmlInfomation getItem(int position) {
        return mXmlInfomation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView mTextViewTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_no_image, null);
            mViewHolder = new ViewHolder();
            mViewHolder.mTextViewTitle = (TextView)convertView.findViewById(R.id.titleTextView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder)convertView.getTag();
        }

        mViewHolder.mTextViewTitle.setText(getItem(position).getXmlTitle());
        return convertView;
    }
}

