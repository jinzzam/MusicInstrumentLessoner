package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.myservice.LoginService;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN_ACT";
    private static LoginActivity instance;
    private static Session session;
    private LoginService loginService;

    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;

    private static MiUserDto userDto;

    {
        instance = this;
        session = Session.getInstance();
        loginService = LoginService.getInstance();
        userDto = new MiUserDto();
    }

    public static LoginActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginProcess();
    }

    private void initView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ivLogin = findViewById(R.id.ivLogin);
    }

    public void loginProcess() {
        ivLogin.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = etEmail.getText().toString();
                String inputPassword = etPassword.getText().toString();
                //로그인 서비스에서 이메일 존재여부 체크
                if (loginService.checkEmail(inputEmail)) {
                    //로그인 서비스에서 패스워드 체크
                    Log.e(TAG, "loginProcess: 이메일 존재여부 체크했습니다.");
                    if (loginService.checkPassword(inputPassword)) {
                        Log.e(TAG, "loginProcess: 이메일이 존재합니다.");
                        Log.e(TAG, "loginProcess: 패스워드 체크합니다.");
                        //메인 액티비티로 이동
                        userDto = loginService.getUserDto();
                        session.setMainUser(userDto);
                        Log.e(TAG, "loginProcess: 로그인 성공!");
                        Toast.makeText(LoginActivity.this.getApplicationContext(), "환영합니다, " + session.getMainUser().getName() + "님.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
                        intent.putExtra("loginActName", session.getMainUser().getName());
                        intent.putExtra("loginActEmail", session.getMainUser().getEmail());
                        LoginActivity.this.startActivity(intent);
                        LoginActivity.this.finish();
                    } else {
                        //토스트 띄우고 로그인 액티비티 그대로
                        Log.e(TAG, "loginProcess: 패스워드가 일치하지 않습니다.");
                        Toast.makeText(LoginActivity.this.getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    //이메일이 존재하지 않는다면
                    //토스트 띄우고 로그인 액티비티 그대로
                    Log.e(TAG, "loginProcess: 이메일이 존재하지 않습니다.");
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "이메일이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
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
