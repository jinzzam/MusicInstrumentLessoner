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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.IpAddress;
import hack.the.wap.musicinstrumentlessoner.session.Session;
import okhttp3.OkHttpClient;

public class TemplateService {
    private static final String TAG = "TEMPLATE_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    private static TemplateService instance;
    private static Session session;
    RequestQueue queue;

    private String getTemplateUrl = "http://" + ipAddress.getIp() + ":3000/api/template/";
    private int musicTemplateId;
    private String owner;
    private String musicTitle;
    private String musician;
    private String guide;
    private HashMap<String, MusicTemplateDto> templateDtoHashMap;
    private MusicTemplateDto templateDto;

    private int templateCount = 0;

    {
        session = Session.getInstance();
        templateDtoHashMap = new HashMap<>();
        templateDto = new MusicTemplateDto();
    }

    private TemplateService() {
        session = Session.getInstance();
    }

    public static TemplateService getInstance() {
        if (instance == null) {
            instance = new TemplateService();
        }
        return instance;
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

    public void getTemplates() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getTemplateUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        musicTemplateId = jsonArray.get(i).getAsJsonObject().get("music_template_id").getAsInt();
                        owner = jsonArray.get(i).getAsJsonObject().get("owner").toString().replace("\"", "");
                        musicTitle = jsonArray.get(i).getAsJsonObject().get("music_title").toString().replace("\"", "");
                        musician = jsonArray.get(i).getAsJsonObject().get("musician").toString().replace("\"", "");
                        guide = jsonArray.get(i).getAsJsonObject().get("guide").toString().replace("\"", "");
                        templateDto = new MusicTemplateDto(musicTemplateId, owner, musicTitle, musician, guide);
                        templateDtoHashMap.put(templateDto.getMusicTitle(), templateDto);
                        Log.e(TAG, "run: " + musician);
                    }
                    session.setTemplates(templateDtoHashMap);
                    Log.e(TAG, "run: " + session.getTemplates().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getTemplateNameById(int id) {
        for (MusicTemplateDto dto : session.getTemplates().values()) {
            if (dto.getMusicTemplateId() == id)
                return dto.getMusicTitle();
        }
        return null;
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
