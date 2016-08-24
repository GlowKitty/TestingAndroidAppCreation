package com.example.glowkitty.testing3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

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

    final MediaPlayer mp = MediaPlayer.create(this, R.raw.objection);

    ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    public void turnPhoenixOnSwitch(View v) {
        try {
            executor.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        executor = Executors.newSingleThreadScheduledExecutor();
        Switch sw = (Switch) findViewById(R.id.switch1);
        ImageView img = (ImageView) findViewById(R.id.phoenix);
        ImageView obj = (ImageView) findViewById(R.id.objection);
        if (sw.isChecked()) {
            img.setImageResource(R.drawable.pointer);
            obj.setImageResource(R.drawable.objection);

            obj.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));
            mp.start();

            Runnable task = new vanish(obj);

            executor.schedule(task, 1000, TimeUnit.MILLISECONDS);
        } else {
            img.setImageResource(R.drawable.sweaty);
            obj.setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setChecked(false);
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
