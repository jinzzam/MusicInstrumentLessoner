package com.example.jinzzam.musicinstrumentlessoner.myactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jinzzam.musicinstrumentlessoner.R;
import com.example.jinzzam.musicinstrumentlessoner.session.Session;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    private static Session session;

    private MainActivity() {

    }

    {
        instance = MainActivity.getInstance();
        session = Session.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static MainActivity getInstance() {
        if (instance == null)
            instance = new MainActivity();
        return instance;
    }
}
