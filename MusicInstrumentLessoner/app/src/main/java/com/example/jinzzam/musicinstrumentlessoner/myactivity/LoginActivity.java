package com.example.jinzzam.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.jinzzam.musicinstrumentlessoner.R;
import com.example.jinzzam.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {

    private static LoginActivity instance;
    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;
    private static Session session;

    {
        instance = LoginActivity.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ivLogin = findViewById(R.id.ivLogin);
        ivLogin.setOnClickListener((v) -> {
            String name = loginProcess(etEmail.getText().toString(), etPassword.getText().toString());
            Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
            intent.putExtra("actLoginName", name);
            intent.putExtra("actLoginEmail", etEmail.getText().toString());
            startActivity(intent);
            finish();
        });
    }

    private String loginProcess(String email, String password) {
        setSession();
        return getResources().getString(R.string.nav_header_title);
    }

    private void setSession() {
        session = Session.getInstance();
    }

    public static LoginActivity getInstance() {
        if (instance == null)
            instance = new LoginActivity();
        return instance;
    }
}
