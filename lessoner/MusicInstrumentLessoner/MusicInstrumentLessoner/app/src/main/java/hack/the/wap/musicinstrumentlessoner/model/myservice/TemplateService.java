package hack.the.wap.musicinstrumentlessoner.model.myservice;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateGuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.session.IpAddress;
import hack.the.wap.musicinstrumentlessoner.session.Session;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TemplateService {
    private static final String TAG = "TEMPLATE_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    private static TemplateService instance;
    private static Session session;

    private String getTemplateUrl = "http://" + ipAddress.getIp() + ":3000/api/template/";
    private String getAssignmentUrl = "http://" + ipAddress.getIp() + ":3000/api/template-assignment/";
    private String getPracticeUrl = "http://" + ipAddress.getIp() + ":3000/api/template-practice/";
    private String getGuideUrl = "http://" + ipAddress.getIp() + ":3000/api/template-guide/";

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

    private int musicTemplatePracticeId;
    private int isDone;
    private int completePercent;

    private String playTime;
    private String comment;

    private HashMap<String, MusicTemplateDto> templateDtoHashMap;
    private MusicTemplateDto templateDto;
    private HashMap<String, MusicTemplateAssignmentDto> assignmentDtoHashMap;
    private MusicTemplateAssignmentDto assignmentDto;
    private TreeMap<Integer, MusicTemplatePracticeDto> practiceDtoHashMap;
    private MusicTemplatePracticeDto practiceDto;
    private MusicTemplateGuideDto guideDto;
    private ArrayList<MusicTemplateGuideDto> guideDtoArrayList;

    private TemplateService() {
        session = Session.getInstance();
        templateDtoHashMap = new HashMap<>();
        templateDto = new MusicTemplateDto();
        assignmentDtoHashMap = new HashMap<>();
        assignmentDto = new MusicTemplateAssignmentDto();
        practiceDtoHashMap = new TreeMap<>();
        practiceDto = new MusicTemplatePracticeDto();
        guideDto = new MusicTemplateGuideDto();
        guideDtoArrayList = new ArrayList<>();
    }

    public static TemplateService getInstance() {
        if (instance == null) {
            instance = new TemplateService();
        }
        return instance;
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

    public void getPractices() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getPracticeUrl)
                            .build();

                    Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        musicTemplatePracticeId = jsonArray.get(i).getAsJsonObject().get("music_template_practice_id").getAsInt();
                        musicTemplateId = jsonArray.get(i).getAsJsonObject().get("music_template_id").getAsInt();
                        studentEmail = jsonArray.get(i).getAsJsonObject().get("student_email").toString().replace("\"", "");
                        innerFileName = jsonArray.get(i).getAsJsonObject().get("inner_filename").toString().replace("\"", "");
                        isDone = jsonArray.get(i).getAsJsonObject().get("is_done").getAsInt();
                        completePercent = jsonArray.get(i).getAsJsonObject().get("complete_percent").getAsInt();
                        practiceDto = new MusicTemplatePracticeDto(musicTemplatePracticeId, musicTemplateId, studentEmail, innerFileName, isDone, completePercent);
                        practiceDtoHashMap.put(musicTemplateId * 10 + musicTemplatePracticeId, practiceDto);
                        Log.e(TAG, "run: 연습 해쉬맵 번호 : " + musicTemplateId * 10 + musicTemplatePracticeId);
                        Log.e(TAG, "run: 현재 저장한 연습 : " + practiceDto.toString());
                        Log.e(TAG, "run: 연습 해쉬맵 : " + practiceDtoHashMap);
                    }
                    session.setTemplatePractices(practiceDtoHashMap);
                    Log.e(TAG, "run: 세션에 저장된 연습 hihi: " + session.getTemplatePractices().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void getGuides() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getGuideUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        musicTemplateId = jsonArray.get(i).getAsJsonObject().get("music_template_id").getAsInt();
                        playTime = jsonArray.get(i).getAsJsonObject().get("play_time").toString().replace("\"", "");
                        comment = jsonArray.get(i).getAsJsonObject().get("comment").toString().replace("\"", "");
                        guideDto = new MusicTemplateGuideDto(musicTemplateId, playTime, comment);
                        guideDtoArrayList.add(i, guideDto);
                        Log.e(TAG, "run: 가이드 해쉬맵 : " + practiceDtoHashMap);
                    }
                    session.setTemplateGuides(guideDtoArrayList);
                    Log.e(TAG, "run: 세션에 저장된 가이드 : " + session.getTemplateGuides().toString());

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

    public MusicTemplateDto getTemplateDto(MusicTemplatePracticeDto practiceDto) {
        for (MusicTemplateDto dto : session.getTemplates().values()) {
            if (dto.getMusicTemplateId() == practiceDto.getMusicTemplateId()) {
                return dto;
            }
        }
        return null;
    }

}
