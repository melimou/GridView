package com.mmousa7.HW2GridView;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private static final int PADDING = 8;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;
    private Context mContext;          // This will have to be passed to the ImageView
    private List<Integer> mThumbIds;   // Adapter must store AdapterView's items
    private String[] mThumbNames;


    // Save the list of image IDs and the context
    public ImageAdapter(Context c, List<Integer> ids, String[] names) {
        mContext = c;
        this.mThumbIds = ids;
        this.mThumbNames = names;
    }

    // Now the methods inherited from abstract superclass BaseAdapter
    // Return the number of items in the Adapter
    @Override
    public int getCount() {
        return mThumbIds.size();
    }

    // Return the data item at position
    @Override
    public Object getItem(int position) {
        return mThumbIds.get(position);
    }

    // Will get called to provide the ID that
    // is passed to OnItemClickListener.onItemClick()
    @Override
    public long getItemId(int position) {
        return mThumbIds.get(position);
    }

    // Return an ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View view, ViewGroup parent) {

//        ImageView imageView = (ImageView) convertView;
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // if convertView's not recycled, initialize some attributes
        if (view == null) {
            view = layoutInflater.inflate(R.layout.single, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.textv);
        ImageView imageView = (ImageView) view.findViewById(R.id.imagev);
        textView.setText(mThumbNames[position]);
        imageView.setImageResource(mThumbIds.get(position));
        return view;
    }
}

