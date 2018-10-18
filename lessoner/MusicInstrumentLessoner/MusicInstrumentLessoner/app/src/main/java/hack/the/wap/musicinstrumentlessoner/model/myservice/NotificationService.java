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

public class NotificationService {
    private static final String TAG = "NOTIFICATION_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    private static Session session = Session.getInstance();
    private static NotificationService instance;

    private String getTemplateUrl;
    private int notificationId;
    private int musicTemplateId;
    private Timestamp registDateTime;
    private String type;
    private String comment;
    private String email;
    private MiNotificationDto notificationDto;
    private ArrayList<MiNotificationDto> notificationDtos;

    {
        getTemplateUrl = "http://" + ipAddress.getIp() + ":3000/api/notification/";
        notificationDtos = new ArrayList<>();
    }

    private NotificationService() {

    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public ArrayList<MiNotificationDto> getNotifications() {
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
                        notificationId = jsonArray.get(i).getAsJsonObject().get("notification_id").getAsInt();
                        musicTemplateId = jsonArray.get(i).getAsJsonObject().get("music_template_id").getAsInt();
                        registDateTime = Timestamp.valueOf(jsonArray.get(i).getAsJsonObject().get("regist_date_time").toString().replace("\"", ""));
                        type = jsonArray.get(i).getAsJsonObject().get("type").toString().replace("\"", "");
                        comment = jsonArray.get(i).getAsJsonObject().get("comment").toString().replace("\"", "");
                        email = jsonArray.get(i).getAsJsonObject().get("email").toString().replace("\"", "");

                        notificationDto = new MiNotificationDto(notificationId, musicTemplateId, email, registDateTime, type, comment);
                        notificationDtos.add(i, notificationDto);
                        Log.e(TAG, "run: " + notificationDto.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        return notificationDtos;
    }

    public boolean isMine(MiNotificationDto dto) {
        if (dto.getEmail().equals(session.getMainUser().getEmail())) {
            return true;
        }
        return false;
    }


}
