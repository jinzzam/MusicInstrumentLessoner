package com.example.jinzzam.musicinstrumentlessoner.session;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jinzzam.musicinstrumentlessoner.R;
import com.example.jinzzam.musicinstrumentlessoner.model.dto.UserDto;
import com.example.jinzzam.musicinstrumentlessoner.myactivity.LoginActivity;

public class Session extends LoginActivity {
    private static Session instance;

    private static final String TAG = "SESSION";
    private EditText etEmail;
    private EditText etPassword;
    private EditText etUsername;
    private Button btnSubmit;
    private RequestQueue queue;
    private StringRequest stringRequest;

    private UserDto user;

    private Session() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.session_test);
        queue = Volley.newRequestQueue(this);
        //initListener();

    }

//    private void initView() {
//        etEmail = findViewById(R.id.etEmail);
//        etUsername = findViewById(R.id.etUsername);
//        etPassword = findViewById(R.id.etPassword);
//        btnSubmit = findViewById(R.id.btnSubmit);
//    }
//
//    private void initListener() {
//        btnSubmit.setOnClickListener((v) -> {
//            Log.e("TAG", "initListener : ");
//            initVolleySet();
//        });
//    }
//
//    private void initVolleySet() {
//        String url = "http://localhost:3000/api/user";
//        url += etEmail.getText().toString() + "/";
//        url += etPassword.getText().toString() + "/";
//        url += etUsername.getText().toString() + "/";
//        stringRequest = new StringRequest(Request.Method.POST, url, (response) -> {
//        }, (error) -> {
//        });
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                0,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//        ));
//        stringRequest.setTag(TAG);
//        queue.add(stringRequest);
//
//    }

    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }
}
