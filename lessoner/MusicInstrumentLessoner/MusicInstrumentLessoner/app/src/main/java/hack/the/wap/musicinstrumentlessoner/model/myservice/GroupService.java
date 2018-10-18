package hack.the.wap.musicinstrumentlessoner.model.myservice;


import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiStudentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.IpAddress;
import hack.the.wap.musicinstrumentlessoner.session.Session;
import okhttp3.OkHttpClient;

public class GroupService {
    private static final String TAG = "GROUP_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    RequestQueue queue;
    private static GroupService instance;
    private static Session session;
    private static TemplateService templateService;

    private final String getGroupUrl;
    private final String getGroupStudentUrl;
    private final String getGroupTeacherUrl;
    private String groupName;
    private String place;
    private String info;
    private String instruments;
    private String genre;
    private MiGroupDto groupDto;
    private HashMap<String, MiGroupDto> groupDtoHashMap;

    int studentCount = 0;
    int teacherCount = 0;
    int teacherTemplateCount = 0;

    {
        getGroupUrl = "http://" + ipAddress.getIp() + ":3000/api/group/";
        getGroupStudentUrl = "http://" + ipAddress.getIp() + ":3000/api/group-student/";
        getGroupTeacherUrl = "http://" + ipAddress.getIp() + ":3000/api/group-teacher/";
        groupDtoHashMap = new HashMap<>();
    }

    private GroupService() {
        session = Session.getInstance();
        templateService = TemplateService.getInstance();
    }

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    public void getGroup() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getGroupUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        groupName = jsonArray.get(i).getAsJsonObject().get("group_name").toString().replace("\"", "");
                        place = jsonArray.get(i).getAsJsonObject().get("place").toString().replace("\"", "");
                        info = jsonArray.get(i).getAsJsonObject().get("info").toString().replace("\"", "");
                        instruments = jsonArray.get(i).getAsJsonObject().get("instruments").toString().replace("\"", "");
                        genre = jsonArray.get(i).getAsJsonObject().get("genre").toString().replace("\"", "");
                        groupDto = new MiGroupDto(groupName, place, info, instruments, genre);
                        groupDtoHashMap.put(groupDto.getGroupName(), groupDto);
                        Log.e(TAG, "run: " + groupDto.toString());
                    }
                    session.setUserGroups(groupDtoHashMap);
                    Log.e(TAG, "run: " + session.getUserGroups().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public boolean isMyGroup(MiGroupDto dto) {
        for (MiTeacherDto teacherDto : session.getGroupTeachers().values()) {
            if (dto.getGroupName().equals(teacherDto.getGroupName()) && teacherDto.getTeacherEmail().equals(session.getMainUser().getEmail())) {
                return true;
            }
        }
        for (MiStudentDto studentDto : session.getGroupStudents().values()) {
            if (dto.getGroupName().equals(studentDto.getGroupName()) && studentDto.getStudentEmail().equals(session.getMainUser().getEmail())) {
                return true;
            }
        }
        return false;
    }

    public int studentCount(String groupName) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getGroupStudentUrl + groupName, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        studentCount++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "studentCount >>>> : " + error);
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "studentCount >>>> : ");
        return studentCount;
    }

    public int teacherCount(String groupName) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getGroupTeacherUrl + groupName, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        teacherCount++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "teacherCount >>>> : " + error);
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "teacherCount >>>> : ");
        return studentCount;
    }

    public int teacherTemplateCount(String groupName) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getGroupTeacherUrl + groupName, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject teacher = response.getJSONObject(i);
                        teacherTemplateCount = templateService.templateCount(teacher.get("teacher_email").toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "teacherCount >>>> : " + error);
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "teacherCount >>>> : ");
        return teacherTemplateCount;
    }
}
