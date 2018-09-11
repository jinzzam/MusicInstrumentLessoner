package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.myservice.VolleyService;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN_ACT";
    private static LoginActivity instance;
    private static Session session = Session.getInstance();
    private static VolleyService volley;
    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;

    {
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        RequestQueue queue = Volley.newRequestQueue(this);
        volley = VolleyService.getInstance(this);
        initView();
        loginButtonEvent();
    }

    private void initView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ivLogin = findViewById(R.id.ivLogin);
    }

    private void loginButtonEvent() {
        ivLogin.setOnClickListener(v -> {
            getUserUrl += etEmail.getText().toString();
            String inputPassword = etPassword.getText().toString();

            volley.userVolleySet();
            volley.fileVolleySet();
            volley.templateVolleySet();
            volley.groupVolleySet();

            Log.e(TAG, "loginButtonEvent >>>> : " + inputPassword);
            String name = loginProcess(inputPassword);
            if (name != null) {
                Toast.makeText(this.getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
                intent.putExtra("actLoginName", userName);
                intent.putExtra("actLoginEmail", userEmail);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this.getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * @param password
     * @return If you success login then return is name. Otherwise, it is null.
     */
    public String loginProcess(String password) {
        if (password.equals(userPassword)) {
            setSession();
            return userName;
        }
        return null;

    }

    public static LoginActivity getInstance() {
        return instance;
    }

    public void setSession() {
        session.setMainUser(userDto);
        if (DebugMode.DEBUG_MOD) {
            DEBUG_SET_SESSION();
        }
    }


    private void DEBUG_SET_SESSION() {
        if (DebugMode.DEBUG_MOD) {

        }
    }
}
