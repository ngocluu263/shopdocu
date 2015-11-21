package com.swd2015.shopdocu.Controller.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.swd2015.shopdocu.Controller.Service.JSONParser;
import com.swd2015.shopdocu.R;

public class MainActivity extends AppCompatActivity {
    public String example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        JSONParser jsonParser = new JSONParser(this);
        jsonParser.execute();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, example, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }



}
