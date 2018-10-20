package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.annotation.SuppressLint;
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
import hack.the.wap.musicinstrumentlessoner.model.myservice.GroupService;
import hack.the.wap.musicinstrumentlessoner.model.myservice.LoginService;
import hack.the.wap.musicinstrumentlessoner.model.myservice.NotificationService;
import hack.the.wap.musicinstrumentlessoner.model.myservice.TemplateService;
import hack.the.wap.musicinstrumentlessoner.model.myservice.UserService;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN_ACT";
    private static LoginActivity instance;
    private static Session session;
    private LoginService loginService;
    private TemplateService templateService;
    private NotificationService notificationService;
    private UserService userService;
    private GroupService groupService;

    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;

    private static MiUserDto userDto;

    {
        instance = this;
        session = Session.getInstance();
        loginService = LoginService.getInstance();
        templateService = TemplateService.getInstance();
        notificationService = NotificationService.getInstance();
        groupService = GroupService.getInstance();
        userService = UserService.getInstance();
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

    @SuppressLint("ClickableViewAccessibility")
    public void loginProcess() {
        ivLogin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String inputEmail = "namolppam@pocket.mon";
                String inputPassword = "1234";
                String inputUserName = "나몰빼미";
                userDto = new MiUserDto(inputEmail, inputPassword, inputUserName);
                session.setMainUser(userDto);
                notificationService.getNotifications();
                templateService.getTemplates();
                groupService.getGroup();
                userService.getUsers();
                Toast.makeText(LoginActivity.this.getApplicationContext(), "환영합니다, " + session.getMainUser().getName() + "님.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
                intent.putExtra("loginActName", session.getMainUser().getName());
                intent.putExtra("loginActEmail", session.getMainUser().getEmail());
                LoginActivity.this.startActivity(intent);
                LoginActivity.this.finish();

//                String inputEmail = etEmail.getText().toString();
//                String inputPassword = etPassword.getText().toString();
//                //로그인 서비스에서 이메일 존재여부 체크 && 로그인 서비스에서 패스워드 체크
//                if (loginService.checkValidUser(inputEmail, inputPassword)) {
//                    userDto = loginService.getUserDto();
//                    session.setMainUser(userDto);
//                    Log.e(TAG, "loginProcess: 로그인 성공! 현재 메인 유저 정보 : " + session.getMainUser());
//                    notificationService.getNotifications();
//                    templateService.getTemplates();
//                    groupService.getGroup();
//                    userService.getUsers();
//                    Toast.makeText(LoginActivity.this.getApplicationContext(), "환영합니다, " + session.getMainUser().getName() + "님.", Toast.LENGTH_LONG).show();
//                    Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
//                    intent.putExtra("loginActName", session.getMainUser().getName());
//                    intent.putExtra("loginActEmail", session.getMainUser().getEmail());
//                    LoginActivity.this.startActivity(intent);
//                    LoginActivity.this.finish();
//                } else {
//                    //이메일이 존재하지 않는다면
//                    //토스트 띄우고 로그인 액티비티 그대로
//                    Log.e(TAG, "loginProcess: 로그인실패.");
//                    Toast.makeText(LoginActivity.this.getApplicationContext(), "로그인 실패.", Toast.LENGTH_LONG).show();
//                }
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
