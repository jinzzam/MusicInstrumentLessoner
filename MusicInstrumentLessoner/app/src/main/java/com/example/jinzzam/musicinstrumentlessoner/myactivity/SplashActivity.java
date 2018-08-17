package com.example.jinzzam.musicinstrumentlessoner.myactivity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.jinzzam.musicinstrumentlessoner.R;


public class SplashActivity extends AppCompatActivity {

    private static SplashActivity instance;

    {
        instance = this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(SplashActivity.getInstance(), LoginActivity.class);
            intent.putExtra("state", "launch");
            startActivity(intent);
            finish();
        }).start();
    }

    public static SplashActivity getInstance() {
        return instance;
    }

}
