package com.mmousa7.HW2GridView;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

//This application uses some deprecated methods.
//See UIViewPager for a more modern version of this application

public class GridLayoutActivity extends Activity {

    protected static final String EXTRA_RES_ID = "POS";
    protected static final String EXTRA_RES_POS = "POS_ID";
    protected static final String PARENT = "PARENT_NAME";
    // not the best way to store the urls but I'm lazy and out of time :(
    private final String[] urls = {"https://en.wikipedia.org/wiki/Jaguar", "https://en.wikipedia.org/wiki/Blue_whale", "https://en.wikipedia.org/wiki/Cheetah", "https://en.wikipedia.org/wiki/Dolphin", "https://en.wikipedia.org/wiki/African_elephant", "https://en.wikipedia.org/wiki/Lion", "https://en.wikipedia.org/wiki/Great_horned_owl", "https://en.wikipedia.org/wiki/Giant_panda", "https://en.wikipedia.org/wiki/Raccoon", "https://en.wikipedia.org/wiki/Great_white_shark", "https://en.wikipedia.org/wiki/Sloth", "https://en.wikipedia.org/wiki/Green_anaconda", "https://en.wikipedia.org/wiki/Zebra", "https://en.wikipedia.org/wiki/Tarantula", "https://en.wikipedia.org/wiki/Kangaroo", "https://en.wikipedia.org/wiki/Tiger", "https://en.wikipedia.org/wiki/Squid", "https://en.wikipedia.org/wiki/Moose", };
    private ArrayList<Integer> mThumbIdsAnimals = new ArrayList<Integer>(
            Arrays.asList(R.drawable.image1, R.drawable.image2,
                    R.drawable.image3, R.drawable.image4, R.drawable.image5,
                    R.drawable.image6, R.drawable.image7, R.drawable.image8,
                    R.drawable.image9, R.drawable.image10, R.drawable.image11,
                    R.drawable.image12, R.drawable.image13, R.drawable.image14,
                    R.drawable.image15, R.drawable.image16, R.drawable.image17, R.drawable.image18));

    private final String[] mThumbNamesAnimals = {"Jaguar","Blue Whale",
            "Cheetah", "Dolphin", "African Elephant", "African Lion", "Great Horned Owl", "Giant Panda", "Racoon",
            "Great White Shark", "Sloth", "Green Anaconda", "Zebra", "Tarantula", "Kangaroo", "Bengal Tiger", "Squid", "Moose"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        // Create a new ImageAdapter and set it as the Adapter for this GridView
        gridview.setAdapter(new ImageAdapter(this, mThumbIdsAnimals, mThumbNamesAnimals));
        // Set an setOnItemClickListener on the GridView
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                viewItem(id, position);
            }
        });

        registerForContextMenu(gridview);
    }

    private void viewItem(long id, int position){
        //Create an Intent to start the ImageViewActivity
        Intent intent = new Intent(GridLayoutActivity.this,
                ImageViewActivity.class);
        // Add the ID of the thumbnail to display as an Intent Extra
        intent.putExtra(EXTRA_RES_ID, (int) id);
        intent.putExtra(EXTRA_RES_POS, (int) position);
        Log.i("GridViewActivity, info", "check id: " + Integer.toString((int) position));
        // Start the ImageViewActivity
        startActivity(intent);
    }

    private void wikipedia(int position){
        Intent intent = new Intent(Intent.ACTION_VIEW);

        Log.i("GridViewActivity, info", "check pos: " + Integer.toString(position));
        intent.setData(Uri.parse(urls[position]));
        // Start the browser
        startActivity(intent);
    }

    private void facts(int position){
        Intent intent = new Intent(GridLayoutActivity.this, InfoViewActivity.class);
        intent.putExtra(EXTRA_RES_POS, (int) position);
        intent.putExtra(PARENT, "GridView");
        startActivity(intent);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        int pos = info.position;
        menu.setHeaderTitle("Options");
        menu.add(0, v.getId(), 0, "View Picture");
        menu.add(0, v.getId(), 0, "Interesting Facts");
        menu.add(0, v.getId(), 0, "Wikipedia Page");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "View Picture") {
            Log.i("GridViewActivity, item click", "check id: " + item.getItemId());
            viewItem(info.id, info.position);
        }
        else if (item.getTitle() == "Interesting Facts") {
            Log.i("GridViewActivity, fact", "check id: " + info.position);
            facts(info.position);
        }
        else if (item.getTitle() == "Wikipedia Page")
            wikipedia(info.position);
        else
            return false;
        return true;
    }
}