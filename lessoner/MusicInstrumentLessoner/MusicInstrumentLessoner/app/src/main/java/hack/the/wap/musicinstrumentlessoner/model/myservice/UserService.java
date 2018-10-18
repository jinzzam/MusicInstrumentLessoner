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

import org.json.JSONArray;
import org.json.JSONObject;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.session.IpAddress;

public class UserService {
    private static final String TAG = "USER_SERVICE";
    private IpAddress ipAddress = new IpAddress();
    RequestQueue queue;
    private static UserService instance;
    private static String getUserUrl;
    private MiUserDto userDto;
    private String userName;

    {
        getUserUrl = "http://" + ipAddress.getIp() + ":3000/api/user";
    }

    private UserService() {

    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }


    public String getUserName(String inputEmail) {
        Log.e(TAG, getUserUrl);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUserUrl + inputEmail, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject user = response.getJSONObject(0);
                    userName = user.get("username").toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "getUserName >>>> : " + error);
            }
        });
        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e(TAG, "mainUserVolleySet >>>> : ");
        return userName;
    }
}
