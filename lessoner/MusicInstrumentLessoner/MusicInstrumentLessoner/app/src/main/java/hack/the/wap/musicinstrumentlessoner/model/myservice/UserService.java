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

import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.IpAddress;
import hack.the.wap.musicinstrumentlessoner.session.Session;
import okhttp3.OkHttpClient;

public class UserService {
    private static final String TAG = "USER_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    private Session session;
    private static UserService instance;


    private static String getUserUrl;
    private String email;
    private String password;
    private String userName;
    private MiUserDto userDto;
    private HashMap<String, MiUserDto> userDtoHashMap;

    private UserService() {
        session = Session.getInstance();
        getUserUrl = "http://" + ipAddress.getIp() + ":3000/api/user";
        userDto = new MiUserDto();
        userDtoHashMap = new HashMap<>();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public void getUsers() {
        new Thread() {
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();

                    okhttp3.Request request = new okhttp3.Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getUserUrl)
                            .build();

                    okhttp3.Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    for (int i = 0; i < jsonArray.size(); i++) {
                        email = jsonArray.get(i).getAsJsonObject().get("email").toString().replace("\"", "");
                        password = jsonArray.get(i).getAsJsonObject().get("password").toString().replace("\"", "");
                        userName = jsonArray.get(i).getAsJsonObject().get("username").toString().replace("\"", "");
                        userDto = new MiUserDto(email, password, userName);
                        userDtoHashMap.put(userDto.getEmail(), userDto);
                        Log.e(TAG, "run: 유저들 해쉬맵 : " + userDtoHashMap);
                    }
                    session.setUsers(userDtoHashMap);
                    Log.e(TAG, "run: 세션에 저장된 유저들 정보 : " + session.getUsers().toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public String getUserName(String inputEmail) {
        for (MiUserDto userDto : session.getUsers().values()) {
            if (userDto.getEmail().equals(inputEmail)) {
                Log.e(TAG, "getUserName: " + userDto.getName() );
                return userDto.getName();
            }
        }
        return null;
    }
}
