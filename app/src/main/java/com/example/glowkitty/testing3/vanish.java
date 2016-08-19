package com.example.glowkitty.testing3;

import android.widget.ImageView;

/**
 * Created by GlowKitty on 8/19/2016.
 */
public class vanish implements Runnable {
    private ImageView objection;
    public void run() {
        objection.setImageResource(0);
    }
    public vanish(ImageView obj) {
        objection = obj;
    }
}
