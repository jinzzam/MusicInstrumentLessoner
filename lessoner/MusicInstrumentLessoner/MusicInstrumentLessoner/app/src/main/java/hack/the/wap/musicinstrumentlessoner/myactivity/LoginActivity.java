package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiFileDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateGuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LOGIN_ACT";
    private static LoginActivity instance;
    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;
    private static Session session = Session.getInstance();
    private static JSONObject user;
    private static String userName;
    private static String userEmail;
    private static String userPassword;
    private static MiUserDto userDto;
    private RequestQueue queue;

    private static String getUserUrl = "http://192.168.43.36:3000/api/miUser/";
    private static String getNotificationUrl = "http://192.168.43.36:3000/api/notification/";
    private static String getFileUrl = "http://192.168.43.36:3000/api/file/";
    private static String getTemplateUrl = "http://192.168.43.36:3000/api/template/";
    private static String getTemplateGuideUrl;
    private static String getTemplateAssignmentUrl;
    private static String getTemplatePracticeUrl;
    private static String getGroupUrl = "http://192.168.43.36:3000/api/group/";

    HashMap<String, MusicTemplateDto> templates = new HashMap<>();
    ArrayList<MiNotificationDto> notifications = new ArrayList<>();
    HashMap<String, MiFileDto> files = new HashMap<>();
    ArrayList<MusicTemplatePracticeDto> practices = new ArrayList<>();
    HashMap<String, MusicTemplateAssignmentDto> assignments = new HashMap<>();
    HashMap<String, MusicTemplateGuideDto> guides = new HashMap<>();

    {
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue = Volley.newRequestQueue(this);
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

            userVolleySet();
            fileVolleySet();
            templateVolleySet();
            groupVolleySet();

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

    private void userVolleySet() {
        Log.e(TAG, getUserUrl);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUserUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    user = response.getJSONObject(0);
                    userName = user.get("username").toString();
                    userEmail = user.get("email").toString();
                    userPassword = user.get("password").toString();
                    userDto = new MiUserDto(userName, userEmail, userPassword);
                    session.setMainUser(userDto);
                    Log.e("TAG", "userVolleySet >>>> : userPassword : " + userPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "userVolleySet >>>> : " + error);
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "userVolleySet >>>> : ");
    }

    private void fileVolleySet() {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getFileUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject file = response.getJSONObject(i);
                        String owner = file.get("owner").toString();
                        String innerFilename = file.get("inner_filename").toString();
                        String outterFilename = file.get("outter_filename").toString();
                        MiFileDto miFileDto = new MiFileDto(owner, innerFilename, outterFilename);
                        files.put(innerFilename, miFileDto);
                    }
                    session.setFiles(files);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "fileVolleySet >>>> : ");
    }

    private void templateVolleySet() {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getTemplateUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject template = response.getJSONObject(i);
                        int musicTemplateId = (int) template.get("music_template_id");
                        String owner = template.get("owner").toString();
                        String musicTitle = template.get("music_title").toString();
                        String musician = template.get("musician").toString();
                        String guide = template.get("guide").toString();
                        MusicTemplateDto musicTemplateDto = new MusicTemplateDto(musicTemplateId, owner, musicTitle, musician, guide);
                        template.put(musicTitle, musicTemplateDto);

                        getTemplateGuideUrl = getTemplateUrl + musicTemplateId + "/guide/";
                        getTemplateAssignmentUrl = getTemplateUrl + musicTemplateId + "/assignment/";
                        getTemplatePracticeUrl = getTemplateUrl + musicTemplateId + "/practice/";

                        notificationVolleySet(musicTemplateId);
                        templateGuideVolleySet(getTemplateGuideUrl);
                        templateAssignmentVolleySet(getTemplateAssignmentUrl);
                        templatePracticeVolleySet(getTemplatePracticeUrl);
                    }
                    session.setTemplates(templates);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "templateVolleySet >>>> : ");
    }

    private void templatePracticeVolleySet(String getTemplatePracticeUrl) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getTemplatePracticeUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject practice = response.getJSONObject(i);
                        int musicTemplatePracticeId = (int) practice.get("music_template_practice_id");
                        int musicTemplateId = (int) practice.get("music_template_id");
                        String studentEmail = practice.get("student_email").toString();
                        String innerFilename = practice.get("inner_filename").toString();
                        boolean isDone = (boolean) practice.get("is_done");
                        int completePercent = (int) practice.get("complete_percent");
                        MusicTemplatePracticeDto musicTemplatePracticeDto = new MusicTemplatePracticeDto(musicTemplatePracticeId, musicTemplateId, studentEmail, innerFilename, isDone, completePercent);
                        practices.add(musicTemplatePracticeDto);
                    }
                    session.setTemplatePractices(practices);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "templatePracticeVolleySet >>>> : ");
    }

    private void templateAssignmentVolleySet(String getTemplateAssignmentUrl) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getTemplateAssignmentUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject assignment = response.getJSONObject(i);
                        String innerFilename = assignment.get("inner_filename").toString();
                        int musicTemplateId = (int) assignment.get("music_template_id");
                        int toDoCount = (int) assignment.get("to_do_count");
                        int doneCount = (int) assignment.get("done_count");
                        int successPercent = (int) assignment.get("success_percent");
                        MusicTemplateAssignmentDto musicTemplateAssignmentDto = new MusicTemplateAssignmentDto(innerFilename, musicTemplateId, toDoCount, doneCount, successPercent);
                        assignments.put(innerFilename, musicTemplateAssignmentDto);
                    }
                    session.setTemplateAssignments(assignments);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "templateAssignmentVolleySet >>>> : ");
    }

    private void templateGuideVolleySet(String getTemplateGuideUrl) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getTemplateGuideUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject guide = response.getJSONObject(i);
                        int musicTemplateId = (int) guide.get("music_template_id");
                        String musicTitle = guide.get("music_title").toString();
                        Time playTime = (Time) guide.get("play_time");
                        String comment = guide.get("comment").toString();
                        MusicTemplateGuideDto musicTemplateGuideDto = new MusicTemplateGuideDto(musicTemplateId, playTime, comment);
                        guides.put(musicTitle, musicTemplateGuideDto);
                    }
                    session.setTemplateGuides(guides);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "templateGuideVolleySet >>>> : ");
    }

    private void notificationVolleySet(int musicTemplateId) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getNotificationUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject notification = response.getJSONObject(i);
                        int miNotificationId = (int) notification.get("mi_notification_id");
                        int musicTemplateId = (int) notification.get("music_template_id");
                        Timestamp registDateTime = (Timestamp) notification.get("regist_data_time");
                        String type = notification.get("type").toString();
                        String comment = notification.get("comment").toString();
                        MiNotificationDto notificationDto = new MiNotificationDto(miNotificationId, musicTemplateId, registDateTime, type, comment);
                        notifications.add(i, notificationDto);
                    }
                    session.setNotifications(notifications);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "notificationVolleySet >>>> : ");
    }


    private void groupVolleySet() {
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
