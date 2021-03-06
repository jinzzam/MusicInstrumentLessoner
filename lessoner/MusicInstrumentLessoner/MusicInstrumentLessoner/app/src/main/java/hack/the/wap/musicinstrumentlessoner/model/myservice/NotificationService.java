package hack.the.wap.musicinstrumentlessoner.model.myservice;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.IpAddress;
import hack.the.wap.musicinstrumentlessoner.session.Session;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NotificationService {
    private static final String TAG = "NOTIFICATION_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    private static Session session;
    private static NotificationService instance;

    private String getTemplateUrl;
    private int notificationId;
    private int musicTemplateId;
    private String registDateTime;
    private String type;
    private String comment;
    private String email;
    private MiNotificationDto notificationDto;
    private ArrayList<MiNotificationDto> notificationDtos;

    private NotificationService() {
        session = Session.getInstance();
        getTemplateUrl = "http://" + ipAddress.getIp() + ":3000/api/notification/";
        notificationDto = new MiNotificationDto();
        notificationDtos = new ArrayList<>();
    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void getNotifications() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getTemplateUrl)
                            .build();

                    Response response = client.newCall(request)
                            .execute();

                    sleep(200);

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        notificationId = jsonArray.get(i).getAsJsonObject().get("mi_notification_id").getAsInt();
                        musicTemplateId = jsonArray.get(i).getAsJsonObject().get("music_template_id").getAsInt();
                        registDateTime = jsonArray.get(i).getAsJsonObject().get("regist_date_time").toString().replace("\"", "");
                        type = jsonArray.get(i).getAsJsonObject().get("type").toString().replace("\"", "");
                        comment = jsonArray.get(i).getAsJsonObject().get("comment").toString().replace("\"", "");
                        notificationDto = new MiNotificationDto(notificationId, musicTemplateId, registDateTime, type, comment);
                        notificationDtos.add(i, notificationDto);
                    }
                    session.setNotifications(notificationDtos);
                    Log.e(TAG, "run: 세션에 저장된 알림들 : " + session.getNotifications());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public boolean isMine(MiNotificationDto dto) {
        if (dto.getType().equals("teacher")) {
            Log.e(TAG, "isMine: 알림 내껀지 확인했습니다.");
            return true;
        }
        return false;
    }


}
