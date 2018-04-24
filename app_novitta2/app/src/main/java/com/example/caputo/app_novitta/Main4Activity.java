package com.example.caputo.app_novitta;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

// TELA SPLASH

public class Main4Activity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Handler splash = new Handler();
        splash.postDelayed(Main4Activity.this, 2000);
    }

    public void run()
    {
        startActivity(new Intent(Main4Activity.this, MainActivity.class));
        finish();
    }
}


