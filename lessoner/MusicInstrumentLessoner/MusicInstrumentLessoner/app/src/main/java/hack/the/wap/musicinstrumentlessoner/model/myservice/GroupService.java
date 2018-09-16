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

import org.json.JSONArray;
import org.json.JSONObject;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiStudentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class GroupService {
    private static final String TAG = "GROUP_SERVICE";
    RequestQueue queue;
    private static GroupService instance;
    private static Session session;
    private static TemplateService templateService;
    private static String getGroupStudentUrl = "http://192.168.43.36:3000/api/group-student/";
    private static String getGroupTeacherUrl = "http://192.168.43.36:3000/api/group-teacher/";
    private MiTeacherDto teacherDto;
    int studentCount = 0;
    int teacherCount = 0;
    int teacherTemplateCount = 0;

    private GroupService(Context context) {
        session = Session.getInstance();
        templateService = TemplateService.getInstance(context);
        queue = Volley.newRequestQueue(context);
    }

    public static GroupService getInstance(Context context) {
        if (instance == null) {
            instance = new GroupService(context);
        }
        return instance;
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
