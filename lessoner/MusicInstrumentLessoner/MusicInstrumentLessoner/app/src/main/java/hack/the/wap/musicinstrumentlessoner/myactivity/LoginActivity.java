package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
    private static VolleyService volleyService;
    private static LoginService loginService = LoginService.getInstance();
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
        volleyService = VolleyService.getInstance(this);
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
            userDto = volleyService.userVolleySet(inputEmail);
            session.setMainUser(userDto);
            //로그인 서비스에서 이메일 존재여부 체크
            if (loginService.checkEmail(inputEmail)) {
                //로그인 서비스에서 패스워드 체크
                if (loginService.checkPassword(inputPassword)) {
                    //메인 액티비티로 이동
                    getDataAll();   //로그인 성공과 동시에 모든 파일 불러와 세션에 저장
                    Toast.makeText(this.getApplicationContext(), "환영합니다, " + session.getMainUser().getName() + "님.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
                    intent.putExtra("loginActName", session.getMainUser().getName());
                    intent.putExtra("loginActEmail", session.getMainUser().getEmail());
                    startActivity(intent);
                    finish();
                } else {
                    //토스트 띄우고 로그인 액티비티 그대로
                    Toast.makeText(this.getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }
            } else {
                //이메일이 존재하지 않는다면
                //토스트 띄우고 로그인 액티비티 그대로
                Toast.makeText(this.getApplicationContext(), "이메일이 존재하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getDataAll() {
        if (!volleyService.fileVolleySet().isEmpty()) {
            session.setFiles(volleyService.fileVolleySet());
        } else {
            Toast.makeText(this.getApplicationContext(), "음악 파일들을 불러오지 못했습니다.", Toast.LENGTH_LONG).show();
        }
        if (!volleyService.templateVolleySet().isEmpty()) {
            session.setTemplates(volleyService.templateVolleySet());
        } else {
            Toast.makeText(this.getApplicationContext(), "템플릿들을 불러오지 못했습니다.", Toast.LENGTH_LONG).show();
        }
        if (!volleyService.groupVolleySet().isEmpty()) {
            session.setUserGroups(volleyService.groupVolleySet());
        } else {
            Toast.makeText(this.getApplicationContext(), "그룹을 불러오지 못했습니다.", Toast.LENGTH_LONG).show();
        }
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
