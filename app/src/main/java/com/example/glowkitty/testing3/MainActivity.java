package com.example.glowkitty.testing3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    public void buttonOnClick(View v) {
        TestingNotification test = new TestingNotification();
        EditText inputTxt = (EditText) findViewById(R.id.editText);
        test.notify(getBaseContext(), inputTxt.getText().toString(), 1);
    }

    //non-initalized variables
    MediaPlayer mp;
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;

    //TODO: make this your own code and make it your own items on the navbar
    private void addDrawerItems() { //add items to the navigation drawer
        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }


    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(); //all for a timer
    public void turnPhoenixOnSwitch(View v) { //more timer
        try {
            executor.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor = Executors.newSingleThreadScheduledExecutor();//timer
        Switch sw = (Switch) findViewById(R.id.switch1);//next 3 intitalize variables for the switch
        ImageView img = (ImageView) findViewById(R.id.phoenix);
        ImageView obj = (ImageView) findViewById(R.id.objection);
        if (sw.isChecked()) {//check if the switch is on or off
            img.setImageResource(R.drawable.pointer);//set images
            obj.setImageResource(R.drawable.objection);//^

            obj.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));//shake dat ass
            mp.start();//play music

            Runnable task = new vanish(obj);//this and next are timer and hide the OBJECTION!
            executor.schedule(task, 1000, TimeUnit.MILLISECONDS);
        } else {
            img.setImageResource(R.drawable.sweaty);// turn everything off and back to normal
            obj.setImageResource(0);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Switch sw = (Switch) findViewById(R.id.switch1);//set the switch
        sw.setChecked(false);//set the switch off justin case
        mp = MediaPlayer.create(this, R.raw.objection);//initalize the mediaplayer to play the OBJECTION! sound

        mDrawerList = (ListView)findViewById(R.id.navList);// start up the whole navigation drawer
        addDrawerItems();
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                // hint: its not
                Uri.parse("android-app://com.example.glowkitty.testing3/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.glowkitty.testing3/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
