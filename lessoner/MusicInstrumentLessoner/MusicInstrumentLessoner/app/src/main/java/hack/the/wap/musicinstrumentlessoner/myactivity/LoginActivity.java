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
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.myservice.LoginService;
import hack.the.wap.musicinstrumentlessoner.myservice.VolleyService;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN_ACT";
    private static LoginActivity instance;
    private static Session session = Session.getInstance();
    private static VolleyService volley;
    private static LoginService loginService;
    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;

    private static MiUserDto userDto;

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
        loginProcess();
    }

    private void initView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ivLogin = findViewById(R.id.ivLogin);
    }

    public void loginProcess() {
        ivLogin.setOnClickListener(v -> {
            String inputEmail = etEmail.getText().toString();
            String inputPassword = etPassword.getText().toString();
            userDto = volley.userVolleySet(inputEmail);
            session.setMainUser(userDto);
            //로그인 서비스에서 이메일 존재여부 체크
            if(loginService.checkEmail(inputEmail)){
                //로그인 서비스에서 패스워드 체크
                if(loginService.checkPassword(inputPassword)) {
                    //메인 액티비티로 이동
                }else{
                    //토스트 띄우고 로그인 액티비티 그대로
                }
            }else{
                //이메일이 존재하지 않는다면
                //토스트 띄우고 로그인 액티비티 그대로
            }



        });


        loginButtonEvent();
    }


    private void loginButtonEvent() {
        ivLogin.setOnClickListener(v -> {
            getUserUrl += etEmail.getText().toString();


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
