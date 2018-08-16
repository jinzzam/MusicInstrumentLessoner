package com.example.jinzzam.musicinstrumentlessoner.myactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jinzzam.musicinstrumentlessoner.R;
import com.example.jinzzam.musicinstrumentlessoner.session.Session;

public class MainActivity extends AppCompatActivity {

    private static Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
    }
}
