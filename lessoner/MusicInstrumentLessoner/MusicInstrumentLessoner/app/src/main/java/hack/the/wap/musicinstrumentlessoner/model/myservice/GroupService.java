package hack.the.wap.musicinstrumentlessoner.model.myservice;


import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    private String studentEmail;
    private String teacherEmail;

    private MiGroupDto groupDto;
    private HashMap<String, MiGroupDto> groupDtoHashMap;
    private MiStudentDto studentDto;
    private ArrayList<MiStudentDto> studentDtoArrayList;
    private MiTeacherDto teacherDto;
    private ArrayList<MiTeacherDto> teacherDtoArrayList;

    int teacherTemplateCount = 0;

    private GroupService() {
        session = Session.getInstance();
        templateService = TemplateService.getInstance();
        getGroupUrl = "http://" + ipAddress.getIp() + ":3000/api/group/";
        getGroupStudentUrl = "http://" + ipAddress.getIp() + ":3000/api/group-student/";
        getGroupTeacherUrl = "http://" + ipAddress.getIp() + ":3000/api/group-teacher/";
        groupDtoHashMap = new HashMap<>();
        studentDtoArrayList = new ArrayList<>();
        teacherDtoArrayList = new ArrayList<>();
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

                    sleep(200);

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
                    }
                    session.setUserGroups(groupDtoHashMap);
                    Log.e(TAG, "run: " + session.getUserGroups().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void getGroupStudents() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getGroupStudentUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();

                    sleep(200);

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        studentEmail = jsonArray.get(i).getAsJsonObject().get("student_email").toString().replace("\"", "");
                        groupName = jsonArray.get(i).getAsJsonObject().get("group_name").toString().replace("\"", "");
                        studentDto = new MiStudentDto(studentEmail, groupName);
                        studentDtoArrayList.add(i, studentDto);
                    }
                    session.setGroupStudents(studentDtoArrayList);
                    Log.e(TAG, "run: " + session.getGroupStudents().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void getGroupTeachers() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getGroupTeacherUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();
                    sleep(200);

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        teacherEmail = jsonArray.get(i).getAsJsonObject().get("teacher_email").toString().replace("\"", "");
                        groupName = jsonArray.get(i).getAsJsonObject().get("group_name").toString().replace("\"", "");
                        teacherDto = new MiTeacherDto(teacherEmail, groupName);
                        teacherDtoArrayList.add(i, teacherDto);
                    }
                    session.setGroupTeachers(teacherDtoArrayList);
                    Log.e(TAG, "run: " + session.getGroupTeachers().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

//    public boolean isMyGroup(MiGroupDto dto) {
//        for (MiTeacherDto teacherDto : session.getGroupTeachers().values()) {
//            if (dto.getGroupName().equals(teacherDto.getGroupName()) && teacherDto.getTeacherEmail().equals(session.getMainUser().getEmail())) {
//                return true;
//            }
//        }
//        for (MiStudentDto studentDto : session.getGroupStudents().values()) {
//            if (dto.getGroupName().equals(studentDto.getGroupName()) && studentDto.getStudentEmail().equals(session.getMainUser().getEmail())) {
//                return true;
//            }
//        }
//        return false;
//    }

    public int studentCount(String groupName) {
        int count = 0;
        for (MiStudentDto dto : session.getGroupStudents()) {
            if (dto.getGroupName().equals(groupName)) {
                count++;
            }
        }
        return count;
    }

    public int teacherCount(String groupName) {
        int count = 0;
        for (MiTeacherDto dto : session.getGroupTeachers()) {
            if (dto.getGroupName().equals(groupName)) {
                count++;
            }
        }
        return count;
    }

    public int teacherTemplateCount(String groupName) {
        int count = 0;
        for (MiTeacherDto dto : session.getGroupTeachers()) {
            for (MusicTemplateDto template : session.getTemplates().values()) {
                if (dto.getTeacherEmail().equals(template.getOwner())) {
                    count++;
                }

            }
        }
        return count;
    }
}
