package hack.the.wap.musicinstrumentlessoner.model.myservice;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

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

    private String getTemplateUrl = "http://" + ipAddress.getIp() + ":3000/api/template/";
    private String getAssignmentUrl = "http://" + ipAddress.getIp() + ":3000/api/template-assignment/";

    private int musicTemplateId;
    private String owner;
    private String musicTitle;
    private String musician;
    private String guide;

    private String innerFileName;
    private int toDoCount;
    private int doneCount;
    private int successPercent;
    private String studentEmail;

    private HashMap<String, MusicTemplateDto> templateDtoHashMap;
    private MusicTemplateDto templateDto;
    private HashMap<String, MusicTemplateAssignmentDto> assignmentDtoHashMap;
    private MusicTemplateAssignmentDto assignmentDto;

    private int templateCount = 0;

    private TemplateService() {
        session = Session.getInstance();
        templateDtoHashMap = new HashMap<>();
        templateDto = new MusicTemplateDto();
        assignmentDtoHashMap = new HashMap<>();
        assignmentDto = new MusicTemplateAssignmentDto();
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
                        Log.e(TAG, "run: 템플릿 해쉬맵 : " + templateDtoHashMap);
                    }
                    session.setTemplates(templateDtoHashMap);
                    Log.e(TAG, "run: 세션에 저장된 템플릿 정보 : " + session.getTemplates().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getAssignments() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getAssignmentUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        innerFileName = jsonArray.get(i).getAsJsonObject().get("music_template_id").toString().replace("\"", "");
                        musicTemplateId = jsonArray.get(i).getAsJsonObject().get("music_template_id").getAsInt();
                        toDoCount = jsonArray.get(i).getAsJsonObject().get("to_do_count").getAsInt();
                        doneCount = jsonArray.get(i).getAsJsonObject().get("done_count").getAsInt();
                        successPercent = jsonArray.get(i).getAsJsonObject().get("success_percent").getAsInt();
                        studentEmail = jsonArray.get(i).getAsJsonObject().get("student_email").toString().replace("\"", "");
                        assignmentDto = new MusicTemplateAssignmentDto(innerFileName, musicTemplateId, studentEmail, toDoCount, doneCount, successPercent);
                        assignmentDtoHashMap.put(getTemplateTitleById(assignmentDto.getMusicTemplateId()), assignmentDto);
                        Log.e(TAG, "run: 과제 해쉬맵 : " + assignmentDtoHashMap);
                    }
                    session.setTemplateAssignments(assignmentDtoHashMap);
                    Log.e(TAG, "run: 세션에 저장된 과제 : " + session.getTemplateAssignments().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getTemplateTitleById(int id) {
        for (MusicTemplateDto dto : session.getTemplates().values()) {
            if (dto.getMusicTemplateId() == id)
                return dto.getMusicTitle();
        }
        return null;
    }

    public MusicTemplateDto getTemplateDto(MusicTemplateAssignmentDto assignmentDto) {
        for (MusicTemplateDto dto : session.getTemplates().values()) {
            if (dto.getMusicTemplateId() == assignmentDto.getMusicTemplateId()) {
                return dto;
            }
        }
        return null;
    }

}
