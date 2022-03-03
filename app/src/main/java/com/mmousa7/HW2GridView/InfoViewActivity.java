package com.mmousa7.HW2GridView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class InfoViewActivity extends Activity {
    protected static final String PARENT = "PARENT_NAME";
    private int pos;

    private final int[] infoList = {R.string.jaguar, R.string.whale, R.string.cheetah, R.string.dolphin, R.string.elephant, R.string.lion, R.string.owl, R.string.panda, R.string.racoon, R.string.shark, R.string.sloth, R.string.snake, R.string.zebra, R.string.tarantula, R.string.kangaroo, R.string.tiger, R.string.squid, R.string.moose};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        TextView infoView = findViewById(R.id.infoText);
        infoView.setTextColor(Color.parseColor("#5c1288"));
        infoView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 22);
        Intent intent = getIntent();
        String parentName = intent.getStringExtra(PARENT);
        if (parentName.equals("GridView"))
            pos = intent.getIntExtra(GridLayoutActivity.EXTRA_RES_POS, 0);
        else
            pos = intent.getIntExtra(ImageViewActivity.EXTRA_RES_POS, 0);
        Log.i("InfoViewActivity", "check pos: " + Integer.toString(pos));
        infoView.setText(infoList[pos]);

    }
}
