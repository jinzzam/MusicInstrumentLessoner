package com.example.jinzzam.volleytest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MAIN";
    private TextView tv;
    private EditText etUserEmail;
    private EditText etUserPassword;
    private Button btnSubmit;
    private String url = "http://192.168.43.36:3000/api/miUser/";
    private static JSONObject userName;
    private static JSONObject userEmail;
    private static JSONObject userPassword;
    private JsonElement parsedUserName;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        buttonEvent();
    }

    private void initView() {
        tv = findViewById(R.id.tv);
        etUserEmail = findViewById(R.id.etEmail);
        etUserPassword = findViewById(R.id.etPassword);
        btnSubmit = findViewById(R.id.btnSend);
    }

    private void buttonEvent() {
        btnSubmit.setOnClickListener((v) -> {
            url += etUserEmail.getText().toString();
            initVolleySet(url);
            Log.e("TAG", "buttonEvent >>>> : ");
            tv.setText(userName.toString());
        });
    }

    private void initVolleySet(String requestUrl) {
        queue = Volley.newRequestQueue(this);
        Log.e(TAG, "initVolleySet >>>> : " + requestUrl);

        final JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JsonParser parser = new JsonParser();
                    userName = response.getJSONObject(0);
                    parsedUserName = parser.parse(userName.toString());
                    Log.e("TAG", "initVolleySet >>>> : " + parsedUserName.isJsonObject());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "initVolleySet >>>> : " + error);
            }

        });
        jsonRequest.setTag(TAG);
        queue.add(jsonRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null)
            queue.cancelAll(TAG);
    }
}
