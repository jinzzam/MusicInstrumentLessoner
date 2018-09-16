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

import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class TemplateService {
    private static final String TAG = "TEMPLATE_SERVICE";
    private static TemplateService instance;
    private static Session session;
    RequestQueue queue;

    private String getTemplateUrl = "http://192.168.43.36:3000/api/template/";

    private String musicTitle;
    private int templateCount = 0;

    private TemplateService(Context context) {
        session = Session.getInstance();
        queue = Volley.newRequestQueue(context);
    }

    public static TemplateService getInstance(Context context) {
        if (instance == null) {
            instance = new TemplateService(context);
        }
        return instance;
    }

    public boolean isMyAssignment(MusicTemplateAssignmentDto assignmentDto) {
        if (assignmentDto.getStudentEmail().equals(session.getMainUser().getEmail())) {
            return true;
        }
        return false;
    }

    public MusicTemplateDto getMyAssignmentTemplate(MusicTemplateAssignmentDto assignmentDto) {
        for (MusicTemplateDto templateDto : session.getTemplates().values()) {
            if (templateDto.getMusicTemplateId() == assignmentDto.getMusicTemplateId()) {
                return templateDto;
            }
        }
        return null;
    }

    public HashMap<String, String> getTemplateLayoutInfo(MusicTemplateDto templateDto, MusicTemplateAssignmentDto assignmentDto) {
        HashMap<String, String> templateLayoutInfo = new HashMap<>();
        templateLayoutInfo.put("musician", templateDto.getMusician());
        templateLayoutInfo.put("musicTitle", templateDto.getMusicTitle());
        templateLayoutInfo.put("toDoCount", assignmentDto.getToDoCount() + "");
        templateLayoutInfo.put("successPercent", assignmentDto.getSuccessPercent() + "");
        templateLayoutInfo.put("teacher", templateDto.getOwner());
        return templateLayoutInfo;
    }

    public String getTemplateNameById(int id) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getTemplateUrl + id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject template = response.getJSONObject(0);
                    musicTitle = template.get("music_title").toString();
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
        Log.e(TAG, "getTemplateNameById >>>> : ");

        return musicTitle;
    }

    public int templateCount(String email) {
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getTemplateUrl + email, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    templateCount++;
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
        Log.e(TAG, "getTemplateNameById >>>> : ");

        return templateCount;

    }
}
