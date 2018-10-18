package hack.the.wap.musicinstrumentlessoner.model.myservice;


import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginService {
    private static final String TAG = "LOGIN_SERVICE";
    private static LoginService instance;

    private String userEmail;
    private String userPassword;
    private String userName;

    private LoginService() {

    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public boolean checkEmail(String inputEmail) {
        new Thread() {
            public void run() {
                String getUserUrl = "http://192.168.43.36:3000/api/user/" + inputEmail;

                try {
                    OkHttpClient client = new OkHttpClient();

                    Request request = new Request.Builder()
                            .addHeader("Authorization", "TEST AUTH")
                            .url(getUserUrl)
                            .build();

                    Response response = client.newCall(request)
                            .execute();

                    String result = response.body().string();

                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonArray = (JsonArray) jsonParser.parse(result);

                    userEmail = jsonArray.get(0).getAsJsonObject().get("email").toString().replace("\"", "");
                    userPassword = jsonArray.get(0).getAsJsonObject().get("password").toString().replace("\"", "");
                    userName = jsonArray.get(0).getAsJsonObject().get("username").toString().replace("\"", "");

                    Log.e(TAG, "run: " + result.toString());
                    Log.e(TAG, "run: " + jsonArray.get(0).getAsJsonObject().get("email"));
                    Log.e(TAG, "run: " + userEmail);
                    Log.e(TAG, "run: " + inputEmail);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        if (inputEmail.equals(userEmail)) {
            return true;
        }
        return false;
    }

    public boolean checkPassword(String inputPassword) {
        if (inputPassword.equals(userPassword)) {
            return true;
        }
        return false;
    }

    public MiUserDto getUserDto() {
        MiUserDto userDto = new MiUserDto(userEmail, userPassword, userName);
        return userDto;
    }
}
