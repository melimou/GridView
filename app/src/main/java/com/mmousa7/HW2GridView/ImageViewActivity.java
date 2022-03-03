package com.mmousa7.HW2GridView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class ImageViewActivity extends Activity {
    protected static final String EXTRA_RES_ID = "POS";
    protected static final String EXTRA_RES_POS = "POS_ID";
    protected static final String PARENT = "PARENT_NAME";
    private int id;
    private int pos;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Get the Intent used to start this Activity
        Intent intent = getIntent();
        id = intent.getIntExtra(GridLayoutActivity.EXTRA_RES_ID, 0);
        pos = intent.getIntExtra(GridLayoutActivity.EXTRA_RES_POS, 0);
        // Make a new ImageView
        // Example of programmatic layout definition
        ImageView imageView = new ImageView(getApplicationContext());
        // Get the ID of the image to display and set it as the image for this ImageView
        Log.i("ImageViewActivity, onCreate", "check id: " + Integer.toString(id));
        imageView.setImageResource(id);
        setContentView(imageView);
        imageView.setOnClickListener(imageClickListener);
    }

    public View.OnClickListener imageClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Log.i("ImageViewActivity, onClick", "check pos: " + Integer.toString(pos));
            info(pos);
        }
    };


    private void info(int pos){
        Intent intent = new Intent(ImageViewActivity.this, InfoViewActivity.class);
        intent.putExtra(EXTRA_RES_POS, (int) pos);
        intent.putExtra(PARENT, "ImageView");
        startActivity(intent);
    }
}
