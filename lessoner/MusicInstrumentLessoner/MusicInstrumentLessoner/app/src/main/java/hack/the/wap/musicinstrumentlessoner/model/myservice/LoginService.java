package hack.the.wap.musicinstrumentlessoner.model.myservice;


import android.content.Context;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;

import hack.the.wap.musicinstrumentlessoner.session.Session;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginService {
    private static final String TAG = "LOGIN_SERVICE";
    private static Session session = Session.getInstance();
    private static LoginService instance;

    private String userEmail;

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
                String getUserUrl = "http://192.168.0.3:3000/api/user/" + inputEmail;

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

                    userEmail = jsonArray.get(0).getAsJsonObject().get("email").toString();
                    Log.e(TAG, "run: " + result.toString());
                    Log.e(TAG, "run: " + jsonArray.get(0).getAsJsonObject().get("email"));
                    Log.e(TAG, "run: " + userEmail);

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
        if (session.getMainUser().getPassword().equals(inputPassword)) {
            return true;
        }
        return false;
    }
}
