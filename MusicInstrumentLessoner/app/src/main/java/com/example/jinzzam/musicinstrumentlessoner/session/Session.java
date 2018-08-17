package com.example.jinzzam.musicinstrumentlessoner.session;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jinzzam.musicinstrumentlessoner.model.dto.UserDto;
import com.example.jinzzam.musicinstrumentlessoner.myactivity.LoginActivity;

public class Session extends LoginActivity {
    private static Session instance;

    private static final String TAG = "SESSION";
    private EditText etEmail;
    private EditText etPassword;
    private EditText etUsername;
    private Button btnLogin;
    private RequestQueue queue;
    private StringRequest stringRequest;

    private UserDto user;

    private Session() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);

    }

    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }
}
