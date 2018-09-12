package hack.the.wap.musicinstrumentlessoner.myservice;

import android.content.Context;
import android.util.Log;

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

import hack.the.wap.musicinstrumentlessoner.model.dto.MiFileDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateGuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;

public class VolleyService {
    private static VolleyService instance;
    private static final String TAG = "VOLLEY_SERVICE";
    RequestQueue queue;

    MiUserDto userDto;
    HashMap<String, MusicTemplateDto> templates = new HashMap<>();
    ArrayList<MiNotificationDto> notifications = new ArrayList<>();
    HashMap<String, MiFileDto> files = new HashMap<>();
    ArrayList<MusicTemplatePracticeDto> practices = new ArrayList<>();
    HashMap<String, MusicTemplateAssignmentDto> assignments = new HashMap<>();
    HashMap<String, MusicTemplateGuideDto> guides = new HashMap<>();
    HashMap<String, MiGroupDto> groups = new HashMap<>();

    private static String getUserUrl = "http://192.168.43.36:3000/api/user/";
    private static String getNotificationUrl = "http://192.168.43.36:3000/api/notification/";
    private static String getFileUrl = "http://192.168.43.36:3000/api/file/";
    private static String getTemplateUrl = "http://192.168.43.36:3000/api/template/";
    private static String getTemplateGuideUrl;
    private static String getTemplateAssignmentUrl;
    private static String getTemplatePracticeUrl;
    private static String getGroupUrl = "http://192.168.43.36:3000/api/group/";


    private VolleyService(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public static VolleyService getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyService(context);
        }
        return instance;
    }

    public MiUserDto userVolleySet(String inputEmail) {
        Log.e(TAG, getUserUrl);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUserUrl + inputEmail, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject user = response.getJSONObject(0);
                    String userName = user.get("username").toString();
                    String userEmail = user.get("email").toString();
                    String userPassword = user.get("password").toString();
                    userDto = new MiUserDto(userName, userEmail, userPassword);

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
        return userDto;
    }

    public HashMap<String, MiFileDto> fileVolleySet() {
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
        return files;
    }

    public HashMap<String, MusicTemplateDto> templateVolleySet() {
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
        return templates;
    }

    public ArrayList<MusicTemplatePracticeDto> templatePracticeVolleySet(String getTemplatePracticeUrl) {
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
        return practices;
    }

    public HashMap<String, MusicTemplateAssignmentDto> templateAssignmentVolleySet(String getTemplateAssignmentUrl) {
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
        return assignments;
    }

    public HashMap<String, MusicTemplateGuideDto> templateGuideVolleySet(String getTemplateGuideUrl) {
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
        return guides;
    }

    public ArrayList<MiNotificationDto> notificationVolleySet(int musicTemplateId) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getNotificationUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject notification = response.getJSONObject(i);
                        int miNotificationId = (int) notification.get("mi_notification_id");
                        int musicTemplateId = (int) notification.get("music_template_id");
                        String email = notification.get("email").toString();
                        Timestamp registDateTime = (Timestamp) notification.get("regist_data_time");
                        String type = notification.get("type").toString();
                        String comment = notification.get("comment").toString();
                        MiNotificationDto notificationDto = new MiNotificationDto(miNotificationId, musicTemplateId, email, registDateTime, type, comment);
                        notifications.add(i, notificationDto);
                    }
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
        return notifications;
    }


    public HashMap groupVolleySet() {
        return groups;
    }
}
