package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MAIN";
    private static LoginActivity instance;
    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;
    private static Session session = Session.getInstance();
    private static String getUserUrl = "http://192.168.43.36:3000/api/miUser/";
    private static JSONObject user;
    private static String userName;
    private static String userEmail;
    private static String userPassword;
    private static MiUserDto userDto;
    private RequestQueue queue;

    {
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue = Volley.newRequestQueue(this);
        initView();
        loginButtonEvent();
    }

    private void initView() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ivLogin = findViewById(R.id.ivLogin);
    }

    private void loginButtonEvent() {
        ivLogin.setOnClickListener(v -> {
            getUserUrl += etEmail.getText().toString();
            String inputPassword = etPassword.getText().toString();
            initVolleySet();
            Log.e("DEBUG", "loginButtonEvent >>>> : " + inputPassword);
            String name = loginProcess(inputPassword);
            if (name != null) {
                Toast.makeText(this.getApplicationContext(), "환영합니다.", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
                intent.putExtra("actLoginName", userName);
                intent.putExtra("actLoginEmail", userEmail);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this.getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initVolleySet() {
        Log.e("TAG", getUserUrl);
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, getUserUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    user = response.getJSONObject(0);
                    userName = user.get("username").toString();
                    userEmail = user.get("email").toString();
                    userPassword = user.get("password").toString();
                    userDto = new MiUserDto(userName, userEmail, userPassword);
                    session.setMainUser(userDto);
                    Log.e("TAG", "initVolleySet >>>> : userPassword : " + userPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "initVolleySet >>>> : " + error);
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));
        jsonArrayRequest.setTag(TAG);
        queue.add(jsonArrayRequest);
        Log.e("TAG", "initVolleySet >>>> : ");
    }


    /**
     * @param password
     * @return If you success login then return is name. Otherwise, it is null.
     */
    public String loginProcess(String password) {
        if (password.equals(userPassword)) {
            setSession();
            return userName;
        }
        return null;

    }

    public static LoginActivity getInstance() {
        return instance;
    }

    public void setSession() {
        session.setMainUser(userDto);
        if (DebugMode.DEBUG_MOD) {
            DEBUG_SET_SESSION();
        }
    }


    private void DEBUG_SET_SESSION() {
        if (DebugMode.DEBUG_MOD) {

            /*
                Debug : All users password
             */
            String password = "1234";

            /*
                insert user data
             */
            String name = userName;
            String email = userEmail;
            // MiUserDto mina = new MiUserDto(name, email, password);

            /*
                Dummy User1 : Choa
             */
            String name1 = getResources().getString(R.string.debug_aoa_choa_name);
            String email1 = getResources().getString(R.string.debug_aoa_choa_email);
            MiUserDto choa = new MiTeacherDto(name1, email1, password);

            /*
                Dummy User2 : Dahyun
             */
            String name2 = getResources().getString(R.string.debug_twice_dahyun_name);
            String email2 = getResources().getString(R.string.debug_twice_dahyun_emai);
            MiUserDto dahyun = new MiTeacherDto(name2, email2, password);

            /*
                Dummy User3 : Segyong
             */
            String name3 = getResources().getString(R.string.debug_segyong_name);
            String email3 = getResources().getString(R.string.debug_segyong_email);
            MiUserDto segyong = new MiUserDto(name3, email3, password);

            /*
                Dummy User4 : gain
             */
            String name4 = getResources().getString(R.string.debug_gain_name);
            String email4 = getResources().getString(R.string.debug_gain_email);
            MiUserDto gain = new MiUserDto(name4, email4, password);

            /*
                Dummy User5 : gain
             */
            String name5 = getResources().getString(R.string.debug_hyoju_name);
            String email5 = getResources().getString(R.string.debug_hyoju_email);
            MiUserDto hyoju = new MiUserDto(name5, email5, password);

            /*
                Dummy User6 : gain
             */
            String name6 = getResources().getString(R.string.debug_kanna_name);
            String email6 = getResources().getString(R.string.debug_kanna_email);
            MiUserDto kanna = new MiUserDto(name6, email6, password);
            /*
                Dummy User7 : jia
             */
            String name7 = getResources().getString(R.string.debug_jia_name);
            String email7 = getResources().getString(R.string.debug_jia_email);
            MiUserDto jia = new MiUserDto(name7, email7, password);
            /*
                Dummy User8 : dongwon
             */
            String name8 = getResources().getString(R.string.debug_dongwon_name);
            String email8 = getResources().getString(R.string.debug_dongwon_email);
            MiUserDto dongwon = new MiUserDto(name8, email8, password);
            /*
                Dummy User9 : gain
             */
            String name9 = getResources().getString(R.string.debug_sohyun_name);
            String email9 = getResources().getString(R.string.debug_sohyun_email);
            MiUserDto sohyun = new MiUserDto(name9, email9, password);
            /*
                Dummy User10 : gain
             */
            String name10 = getResources().getString(R.string.debug_jihyun_name);
            String email10 = getResources().getString(R.string.debug_jihyun_email);
            MiUserDto jihyun = new MiUserDto(name10, email10, password);
            /*
                Dummy User11 : gain
             */
            String name11 = getResources().getString(R.string.debug_sarang_name);
            String email11 = getResources().getString(R.string.debug_sarang_email);
            MiUserDto sarang = new MiUserDto(name11, email11, password);
            /*
                Dummy User12 : gain
             */
            String name12 = getResources().getString(R.string.debug_woobin_name);
            String email12 = getResources().getString(R.string.debug_woobin_email);
            MiUserDto woobin = new MiUserDto(name12, email12, password);
            /*
                Dummy User13 : gain
             */
            String name13 = getResources().getString(R.string.debug_dongsuck_name);
            String email13 = getResources().getString(R.string.debug_dongsuck_email);
            MiUserDto dongsuck = new MiUserDto(name13, email13, password);
            /*
                Dummy User14 : gain
             */
            String name14 = getResources().getString(R.string.debug_jiho_name);
            String email14 = getResources().getString(R.string.debug_jiho_email);
            MiUserDto jiho = new MiUserDto(name14, email14, password);
            /*
                Dummy User15 : gain
             */
            String name15 = getResources().getString(R.string.debug_taehie_name);
            String email15 = getResources().getString(R.string.debug_taehie_email);
            MiUserDto taehie = new MiUserDto(name15, email15, password);
            /*
                Dummy User16 : gain
             */
            String name16 = getResources().getString(R.string.debug_youjung_name);
            String email16 = getResources().getString(R.string.debug_youjung_email);
            MiUserDto youjung = new MiUserDto(name16, email16, password);
            /*
                Dummy User17 : gain
             */
            String name17 = getResources().getString(R.string.debug_joohyun_name);
            String email17 = getResources().getString(R.string.debug_joohyun_email);
            MiTeacherDto joohyun = new MiTeacherDto(name17, email17, password);
            /*
                Dummy User18 : gain
             */
            String name18 = getResources().getString(R.string.debug_jeny_name);
            String email18 = getResources().getString(R.string.debug_jeny_email);
            MiTeacherDto jeny = new MiTeacherDto(name18, email18, password);


            /*
                Dummy Template1 : Moonlight3rd
             */
            MiUserDto owner1 = choa;
            String musicTitle1 = getResources().getString(R.string.debug_beethoven_music);
            String musician1 = getResources().getString(R.string.debug_beethoven_name);
            String tMain1 = getResources().getString(R.string.debug_beethoven_main);
            String tSub1 = getResources().getString(R.string.debug_beethoven_sub);
            MusicTemplateDto moonlight3rd = new MusicTemplateDto(owner1, musicTitle1, musician1, tMain1, tSub1);
            TemplatePracticeDto m3rdPractice = new TemplatePracticeDto(1, getResources().getString(R.string.debug_tempate_detail_moonlight_index0));
            TemplatePracticeDto m3rdPractice2 = new TemplatePracticeDto(2, getResources().getString(R.string.debug_tempate_detail_moonlight_index1));
            m3rdPractice.setPercent(30);
            m3rdPractice2.setPercent(30);
            m3rdPractice.addData(getResources().getString(R.string.debug_haydn_exp_data_time1), getResources().getString(R.string.debug_haydn_exp_data_main1));
            m3rdPractice.addData(getResources().getString(R.string.debug_haydn_exp_data_time2), getResources().getString(R.string.debug_haydn_exp_data_main2));
            m3rdPractice.addData(getResources().getString(R.string.debug_haydn_exp_data_time3), getResources().getString(R.string.debug_haydn_exp_data_main3));
            m3rdPractice.addData(getResources().getString(R.string.debug_haydn_exp_data_time4), getResources().getString(R.string.debug_haydn_exp_data_main4));
            m3rdPractice.addData(getResources().getString(R.string.debug_haydn_exp_data_time5), getResources().getString(R.string.debug_haydn_exp_data_main5));
            moonlight3rd.getTemplatePractices().set(0, m3rdPractice);
            moonlight3rd.getTemplatePractices().set(1, m3rdPractice2);
            ((MiTeacherDto) owner1).addTemplate(moonlight3rd);

             /*
                Dummy Template2 : SurpriseSymphony
             */
            MiUserDto owner2 = dahyun;
            String musicTitle2 = getResources().getString(R.string.debug_haydn_music);
            String musician2 = getResources().getString(R.string.debug_haydn_name);
            String tMain2 = getResources().getString(R.string.debug_haydn_main);
            String tSub2 = getResources().getString(R.string.debug_haydn_sub);
            GuideDto guide2 = new GuideDto(getResources().getString(R.string.debug_haydn_explain), "time");
            guide2.addData(getResources().getString(R.string.debug_haydn_exp_data_time1), getResources().getString(R.string.debug_haydn_exp_data_main1));
            guide2.addData(getResources().getString(R.string.debug_haydn_exp_data_time2), getResources().getString(R.string.debug_haydn_exp_data_main2));
            guide2.addData(getResources().getString(R.string.debug_haydn_exp_data_time3), getResources().getString(R.string.debug_haydn_exp_data_main3));
            guide2.addData(getResources().getString(R.string.debug_haydn_exp_data_time4), getResources().getString(R.string.debug_haydn_exp_data_main4));
            guide2.addData(getResources().getString(R.string.debug_haydn_exp_data_time5), getResources().getString(R.string.debug_haydn_exp_data_main5));
            MusicTemplateDto surpriseSymphony = new MusicTemplateDto(owner2, musicTitle2, musician2, tMain2, tSub2, guide2);
            ((MiTeacherDto) owner2).addTemplate(surpriseSymphony);

            /*
                Dummy Template3 : CzernyNo95
             */
            MiUserDto owner3 = choa;
            String musicTitle3 = getResources().getString(R.string.debug_czerny_music);
            String musician3 = getResources().getString(R.string.debug_czerny_name);
            String tMain3 = getResources().getString(R.string.debug_czerny_main);
            String tSub3 = getResources().getString(R.string.debug_czerny_sub);
            MusicTemplateDto czernyNo95 = new MusicTemplateDto(owner3, musicTitle3, musician3, tMain3, tSub3);
            ((MiTeacherDto) owner3).addTemplate(czernyNo95);

            /*
                Dummy Template4 : Canon
             */
            MiUserDto owner4 = choa;
            String musicTitle4 = getResources().getString(R.string.debug_pachelbel_music);
            String musician4 = getResources().getString(R.string.debug_pachelbel_name);
            String tMain4 = getResources().getString(R.string.debug_pachelbel_main);
            String tSub4 = getResources().getString(R.string.debug_pachelbel_sub);
            MusicTemplateDto canon = new MusicTemplateDto(owner4, musicTitle4, musician4, tMain4, tSub4, 20);
            ((MiTeacherDto) owner4).addTemplate(canon);

            /*
                Dummy Template5 : Canon_rock
             */
            MiUserDto owner5 = joohyun;
            String musicTitle5 = getResources().getString(R.string.debug_canon_rock_music);
            String musician5 = getResources().getString(R.string.debug_canon_rock_name);
            String tMain5 = getResources().getString(R.string.debug_canon_rock_main);
            String tSub5 = getResources().getString(R.string.debug_canon_rock_sub);
            MusicTemplateDto canonRock = new MusicTemplateDto(owner5, musicTitle5, musician5, tMain5, tSub5, 20);
            ((MiTeacherDto) owner5).addTemplate(canonRock);

            /*
                Dummy Template6 : Taryeong
             */
            MiUserDto owner6 = jeny;
            String musicTitle6 = getResources().getString(R.string.debug_taryeong_music);
            String musician6 = getResources().getString(R.string.debug_taryeong_name);
            String tMain6 = getResources().getString(R.string.debug_taryeong_main);
            String tSub6 = getResources().getString(R.string.debug_taryeong_sub);
            MusicTemplateDto folkSong = new MusicTemplateDto(owner6, musicTitle6, musician6, tMain6, tSub6, 20);
            ((MiTeacherDto) owner6).addTemplate(folkSong);

            /*
                Dummy Template7 : Metallica
             */
            MiUserDto owner7 = joohyun;
            String musicTitle7 = getResources().getString(R.string.debug_taryeong_music);
            String musician7 = getResources().getString(R.string.debug_taryeong_name);
            String tMain7 = getResources().getString(R.string.debug_taryeong_main);
            String tSub7 = getResources().getString(R.string.debug_taryeong_sub);
            MusicTemplateDto enterTheSandman = new MusicTemplateDto(owner7, musicTitle7, musician7, tMain7, tSub7, 20);
            ((MiTeacherDto) owner7).addTemplate(enterTheSandman);

             /*
                Dummy Template8 : YoonSuckJoon
             */
            MiUserDto owner8 = jeny;
            String musicTitle8 = getResources().getString(R.string.debug_airplane_music);
            String musician8 = getResources().getString(R.string.debug_airplane_name);
            String tMain8 = getResources().getString(R.string.debug_airplane_main);
            String tSub8 = getResources().getString(R.string.debug_airplane_sub);
            MusicTemplateDto airplain = new MusicTemplateDto(owner8, musicTitle8, musician8, tMain8, tSub8, 20);
            ((MiTeacherDto) owner8).addTemplate(airplain);

             /*
                Dummy Template9 : Chopin
             */
            MiUserDto owner9 = dahyun;
            String musicTitle9 = getResources().getString(R.string.debug_fantasia_music);
            String musician9 = getResources().getString(R.string.debug_fantasia_name);
            String tMain9 = getResources().getString(R.string.debug_fantasia_main);
            String tSub9 = getResources().getString(R.string.debug_fantasia_sub);
            MusicTemplateDto chopin = new MusicTemplateDto(owner9, musicTitle9, musician9, tMain9, tSub9, 20);
            ((MiTeacherDto) owner9).addTemplate(chopin);

             /*
                Dummy Template10 : Celine dion
             */
            MiUserDto owner10 = dahyun;
            String musicTitle10 = getResources().getString(R.string.debug_mhwgo_music);
            String musician10 = getResources().getString(R.string.debug_mhwgo_name);
            String tMain10 = getResources().getString(R.string.debug_mhwgo_main);
            String tSub10 = getResources().getString(R.string.debug_mhwgo_sub);
            MusicTemplateDto celineDion = new MusicTemplateDto(owner10, musicTitle10, musician10, tMain10, tSub10, 20);
            ((MiTeacherDto) owner10).addTemplate(celineDion);

            /*
                Dummy Template11 : Mozart
             */
            MiUserDto owner11 = dahyun;
            String musicTitle11 = getResources().getString(R.string.debug_turkie_music);
            String musician11 = getResources().getString(R.string.debug_turkie_name);
            String tMain11 = getResources().getString(R.string.debug_turkie_main);
            String tSub11 = getResources().getString(R.string.debug_turkie_sub);
            MusicTemplateDto mozart = new MusicTemplateDto(owner11, musicTitle11, musician11, tMain11, tSub11, 21);
            ((MiTeacherDto) owner11).addTemplate(mozart);

             /*
                Dummy Template12 : Vivaldi
             */
            MiUserDto owner12 = jeny;
            String musicTitle12 = getResources().getString(R.string.debug_vivaldi_music);
            String musician12 = getResources().getString(R.string.debug_vivaldi_name);
            String tMain12 = getResources().getString(R.string.debug_vivaldi_main);
            String tSub12 = getResources().getString(R.string.debug_vivaldi_sub);
            MusicTemplateDto vivaldi = new MusicTemplateDto(owner12, musicTitle12, musician12, tMain12, tSub12, 22);
            ((MiTeacherDto) owner12).addTemplate(vivaldi);

             /*
                Dummy Template13 : RealSanae
             */
            MiUserDto owner13 = joohyun;
            String musicTitle13 = getResources().getString(R.string.debug_realsanae_music);
            String musician13 = getResources().getString(R.string.debug_realsanae_name);
            String tMain13 = getResources().getString(R.string.debug_realsanae_main);
            String tSub13 = getResources().getString(R.string.debug_realsanae_sub);
            MusicTemplateDto realSanae = new MusicTemplateDto(owner13, musicTitle13, musician13, tMain13, tSub13, 10);
            ((MiTeacherDto) owner13).addTemplate(realSanae);

             /*
                Dummy Template14 : Wap
             */
            MiUserDto owner14 = joohyun;
            String musicTitle14 = getResources().getString(R.string.debug_wapthehack_music);
            String musician14 = getResources().getString(R.string.debug_wapthehack_name);
            String tMain14 = getResources().getString(R.string.debug_wapthehack_main);
            String tSub14 = getResources().getString(R.string.debug_wapthehack_sub);
            MusicTemplateDto wap = new MusicTemplateDto(owner14, musicTitle14, musician14, tMain14, tSub14, 10);
            ((MiTeacherDto) owner14).addTemplate(wap);

            /*
                Dummy Notification1 : not1
             */
            boolean trueUser1 = true;
            MusicTemplateDto template1 = moonlight3rd;
            String nMain1 = getResources().getString(R.string.teacher_upload_music);
            String nDate1 = getResources().getString(R.string.debug_aoa_choa_date1);
            MiNotificationDto not1 = new MiNotificationDto(trueUser1, template1, nMain1, nDate1);

            /*
                Dummy Notification2 : not2
            */
            boolean trueUser2 = false;
            MusicTemplateDto template2 = czernyNo95;
            String nMain2 = getResources().getString(R.string.debug_mi_main);
            String nDate2 = getResources().getString(R.string.debug_mi_date1);
            MiNotificationDto not2 = new MiNotificationDto(trueUser2, template2, nMain2, nDate2);

            /*
                Dummy Notification3 : not3
            */
            boolean trueUser3 = true;
            MusicTemplateDto template3 = celineDion;
            String nMain3 = getResources().getString(R.string.friend_complete_music);
            String nDate3 = getResources().getString(R.string.debug_twice_dahyun_date1);
            MiNotificationDto not3 = new MiNotificationDto(trueUser3, template3, nMain3, nDate3);

            /*
                Dummy Group1 : Libre
             */
            String gName1 = getResources().getString(R.string.debug_libre_name);
            String gMain1 = getResources().getString(R.string.debug_libre_detail_main);
            String gSub1 = getResources().getString(R.string.debug_libre_detail_sub);
            MiGroupDto libre = new MiGroupDto(gName1, gMain1, gSub1);
            libre.addTeacher((MiTeacherDto) choa);
            libre.addUser(kanna);
            libre.addUser(segyong);
            libre.addUser(gain);
            libre.addInstrument(getResources().getString(R.string.piano));
            libre.addInstrument(getResources().getString(R.string.violin));
            libre.addInstrument(getResources().getString(R.string.flute));
            libre.addGenre(getResources().getString(R.string.string_music));
            libre.addGenre(getResources().getString(R.string.classic));
            libre.addGenre(getResources().getString(R.string.pipe_music));

            /*
                Dummy Group2 : SMMA
             */
            String gName2 = getResources().getString(R.string.debug_smma_name);
            String gMain2 = getResources().getString(R.string.debug_smma_detail_main);
            String gSub2 = getResources().getString(R.string.debug_smma_detail_sub);
            MiGroupDto smma = new MiGroupDto(gName2, gMain2, gSub2);
            smma.addTeacher((MiTeacherDto) dahyun);
            smma.addUser(hyoju);
            smma.addInstrument(getResources().getString(R.string.flute));
            smma.addInstrument(getResources().getString(R.string.trumpet));
            smma.addInstrument(getResources().getString(R.string.saxophone));
            smma.addGenre(getResources().getString(R.string.jazz_band));
            smma.addGenre(getResources().getString(R.string.petite_harmonie));
            smma.addGenre(getResources().getString(R.string.pipe_music));

            /*
                Dummy Group3 : with
             */
            String gName3 = getResources().getString(R.string.debug_with_name);
            String gMain3 = getResources().getString(R.string.debug_with_detail_main);
            String gSub3 = getResources().getString(R.string.debug_with_detail_sub);
            MiGroupDto with = new MiGroupDto(gName3, gMain3, gSub3);
            with.addTeacher(joohyun);
            with.addTeacher(jeny);
            with.addUser(hyoju);
            with.addUser(youjung);
            with.addUser(jiho);
            with.addUser(woobin);
            with.addUser(sarang);
            with.addInstrument(getResources().getString(R.string.trumpet));
            with.addInstrument(getResources().getString(R.string.saxophone));
            with.addGenre(getResources().getString(R.string.jazz_band));
            with.addGenre(getResources().getString(R.string.pipe_music));
            with.setMine(true);

             /*
                Dummy Group4 : pmc
             */
            String gName4 = getResources().getString(R.string.debug_pmc_name);
            String gMain4 = getResources().getString(R.string.debug_pmc_detail_main);
            String gSub4 = getResources().getString(R.string.debug_pmc_detail_sub);
            MiGroupDto pmc = new MiGroupDto(gName4, gMain4, gSub4);
            pmc.addTeacher((MiTeacherDto) choa);
            pmc.addUser(kanna);
            pmc.addUser(dongwon);
            pmc.addUser(jihyun);
            pmc.addUser(taehie);
            pmc.addUser(sohyun);
            pmc.addInstrument(getResources().getString(R.string.trumpet));
            pmc.addInstrument(getResources().getString(R.string.saxophone));
            pmc.addGenre(getResources().getString(R.string.jazz_band));
            pmc.addGenre(getResources().getString(R.string.pipe_music));
            pmc.setMine(true);

            /*
                Dummy Group5 : brain
             */
            String gName5 = getResources().getString(R.string.debug_brain_name);
            String gMain5 = getResources().getString(R.string.debug_brain_detail_main);
            String gSub5 = getResources().getString(R.string.debug_brain_detail_sub);
            MiGroupDto brain = new MiGroupDto(gName5, gMain5, gSub5);
            brain.addTeacher(jeny);
            brain.addUser(gain);
            brain.addUser(segyong);
            brain.addUser(dongsuck);
            brain.addUser(jia);
            brain.addUser(hyoju);
            brain.addInstrument(getResources().getString(R.string.saxophone));
            brain.addGenre(getResources().getString(R.string.pipe_music));
            brain.setMine(true);


            /*
                Insert Template data
             */
            HashMap<String, MusicTemplateDto> templates = new HashMap<>();
            templates.put(moonlight3rd.getMusicTitle(), moonlight3rd);
            templates.put(surpriseSymphony.getMusicTitle(), surpriseSymphony);
            templates.put(czernyNo95.getMusicTitle(), czernyNo95);
            templates.put(canon.getMusicTitle(), canon);
            templates.put(canonRock.getMusicTitle(), canonRock);
            templates.put(folkSong.getMusicTitle(), folkSong);
            templates.put(enterTheSandman.getMusicTitle(), enterTheSandman);
            templates.put(airplain.getMusicTitle(), airplain);
            templates.put(chopin.getMusicTitle(), chopin);
            templates.put(celineDion.getMusicTitle(), celineDion);
            templates.put(mozart.getMusicTitle(), mozart);
            templates.put(vivaldi.getMusicTitle(), vivaldi);
            templates.put(realSanae.getMusicTitle(), realSanae);
            templates.put(wap.getMusicTitle(), wap);

            /*
                Insert Notification data
             */
            ArrayList<MiNotificationDto> notifications = new ArrayList<>();
            notifications.add(not1);
            notifications.add(not2);
            notifications.add(not3);

            /*
                Insert User Group data
             */
            HashMap<String, MiGroupDto> userGroups = new HashMap<>();
            userGroups.put(libre.getName(), libre);
            userGroups.put(smma.getName(), smma);
            userGroups.put(with.getName(), with);
            userGroups.put(pmc.getName(), pmc);
            userGroups.put(brain.getName(), brain);

            session.setMainUser(userDto);
            session.setTemplates(templates);
            session.setNotifications(notifications);
            session.setUserGroups(userGroups);
        }
    }
}
