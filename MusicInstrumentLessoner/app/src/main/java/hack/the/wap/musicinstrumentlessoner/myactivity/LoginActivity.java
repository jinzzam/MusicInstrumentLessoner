package hack.the.wap.musicinstrumentlessoner.myactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.R;
import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.GuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.NotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.TeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplatePracticeDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.UserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.UserGroupDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginActivity extends AppCompatActivity {
    private static LoginActivity instance;
    private static ImageView ivLogin;
    private static EditText etEmail;
    private static EditText etPassword;
    private static Session session;

    /*
     init block
     */ {
        instance = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        ivLogin = findViewById(R.id.ivLogin);
        ivLogin.setOnClickListener(v -> {
            String name = loginProcess(etEmail.getText().toString(), etPassword.getText().toString());
            if (name != null) {
                Intent intent = new Intent(LoginActivity.getInstance(), MainActivity.class);
                intent.putExtra("actLoginName", name);
                intent.putExtra("actLoginEmail", etEmail.getText().toString());
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * @param email
     * @param password
     * @return If you success login then return is name. Otherwise, it is null.
     */
    public String loginProcess(String email, String password) {
        setSession();
        return getResources().getString(R.string.nav_header_title);
    }

    public static LoginActivity getInstance() {
        return instance;
    }

    public void setSession() {
        session = Session.getInstance();
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
            String name = getResources().getString(R.string.nav_header_title);
            String email = getResources().getString(R.string.nav_header_subtitle);
            UserDto mina = new UserDto(name, email, password);

            /*
                Dummy User1 : Choa
             */
            String name1 = getResources().getString(R.string.debug_aoa_choa_name);
            String email1 = getResources().getString(R.string.debug_aoa_choa_email);
            UserDto choa = new TeacherDto(name1, email1, password);

            /*
                Dummy User2 : Dahyun
             */
            String name2 = getResources().getString(R.string.debug_twice_dahyun_name);
            String email2 = getResources().getString(R.string.debug_twice_dahyun_emai);
            UserDto dahyun = new TeacherDto(name2, email2, password);

            /*
                Dummy User3 : Segyong
             */
            String name3 = getResources().getString(R.string.debug_segyong_name);
            String email3 = getResources().getString(R.string.debug_segyong_email);
            UserDto segyong = new UserDto(name3, email3, password);
            
            /*
                Dummy User4 : gain
             */
            String name4 = getResources().getString(R.string.debug_gain_name);
            String email4 = getResources().getString(R.string.debug_gain_email);
            UserDto gain = new UserDto(name4, email4, password);
            
            /*
                Dummy User5 : gain
             */
            String name5 = getResources().getString(R.string.debug_hyoju_name);
            String email5 = getResources().getString(R.string.debug_hyoju_email);
            UserDto hyoju = new UserDto(name5, email5, password);
            
            /*
                Dummy User6 : gain
             */
            String name6 = getResources().getString(R.string.debug_kanna_name);
            String email6 = getResources().getString(R.string.debug_kanna_email);
            UserDto kanna = new UserDto(name6, email6, password);
            /*
                Dummy User7 : jia
             */
            String name7 = getResources().getString(R.string.debug_jia_name);
            String email7 = getResources().getString(R.string.debug_jia_email);
            UserDto jia = new UserDto(name7, email7, password);
            /*
                Dummy User8 : dongwon
             */
            String name8 = getResources().getString(R.string.debug_dongwon_name);
            String email8 = getResources().getString(R.string.debug_dongwon_email);
            UserDto dongwon = new UserDto(name8, email8, password);
            /*
                Dummy User9 : gain
             */
            String name9 = getResources().getString(R.string.debug_sohyun_name);
            String email9 = getResources().getString(R.string.debug_sohyun_email);
            UserDto sohyun = new UserDto(name9, email9, password);
            /*
                Dummy User10 : gain
             */
            String name10 = getResources().getString(R.string.debug_jihyun_name);
            String email10 = getResources().getString(R.string.debug_jihyun_email);
            UserDto jihyun = new UserDto(name10, email10, password);
            /*
                Dummy User11 : gain
             */
            String name11 = getResources().getString(R.string.debug_sarang_name);
            String email11 = getResources().getString(R.string.debug_sarang_email);
            UserDto sarang = new UserDto(name11, email11, password);
            /*
                Dummy User12 : gain
             */
            String name12 = getResources().getString(R.string.debug_woobin_name);
            String email12 = getResources().getString(R.string.debug_woobin_email);
            UserDto woobin = new UserDto(name12, email12, password);
            /*
                Dummy User13 : gain
             */
            String name13 = getResources().getString(R.string.debug_dongsuck_name);
            String email13 = getResources().getString(R.string.debug_dongsuck_email);
            UserDto dongsuck = new UserDto(name13, email13, password);
            /*
                Dummy User14 : gain
             */
            String name14 = getResources().getString(R.string.debug_jiho_name);
            String email14 = getResources().getString(R.string.debug_jiho_email);
            UserDto jiho = new UserDto(name14, email14, password);
            /*
                Dummy User15 : gain
             */
            String name15 = getResources().getString(R.string.debug_taehie_name);
            String email15 = getResources().getString(R.string.debug_taehie_email);
            UserDto taehie = new UserDto(name15, email15, password);
            /*
                Dummy User16 : gain
             */
            String name16 = getResources().getString(R.string.debug_youjung_name);
            String email16 = getResources().getString(R.string.debug_youjung_email);
            UserDto youjung = new UserDto(name16, email16, password);
            /*
                Dummy User17 : gain
             */
            String name17 = getResources().getString(R.string.debug_joohyun_name);
            String email17 = getResources().getString(R.string.debug_joohyun_email);
            TeacherDto joohyun = new TeacherDto(name17, email17, password);
            /*
                Dummy User18 : gain
             */
            String name18 = getResources().getString(R.string.debug_jeny_name);
            String email18 = getResources().getString(R.string.debug_jeny_email);
            TeacherDto jeny = new TeacherDto(name18, email18, password);
            

            /*
                Dummy Template1 : Moonlight3rd
             */
            UserDto owner1 = choa;
            String musicTitle1 = getResources().getString(R.string.debug_beethoven_music);
            String musician1 = getResources().getString(R.string.debug_beethoven_name);
            String tMain1 = getResources().getString(R.string.debug_beethoven_main);
            String tSub1 = getResources().getString(R.string.debug_beethoven_sub);
            TemplateDto moonlight3rd = new TemplateDto(owner1, musicTitle1, musician1, tMain1, tSub1);
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
            ((TeacherDto) owner1).addTemplate(moonlight3rd);

             /*
                Dummy Template2 : SurpriseSymphony
             */
            UserDto owner2 = dahyun;
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
            TemplateDto surpriseSymphony = new TemplateDto(owner2, musicTitle2, musician2, tMain2, tSub2, guide2);
            ((TeacherDto) owner2).addTemplate(surpriseSymphony);
            
            /*
                Dummy Template3 : CzernyNo95
             */
            UserDto owner3 = choa;
            String musicTitle3 = getResources().getString(R.string.debug_czerny_music);
            String musician3 = getResources().getString(R.string.debug_czerny_name);
            String tMain3 = getResources().getString(R.string.debug_czerny_main);
            String tSub3 = getResources().getString(R.string.debug_czerny_sub);
            TemplateDto czernyNo95 = new TemplateDto(owner3, musicTitle3, musician3, tMain3, tSub3);
            ((TeacherDto) owner3).addTemplate(czernyNo95);
            
            /*
                Dummy Template4 : Canon
             */
            UserDto owner4 = choa;
            String musicTitle4 = getResources().getString(R.string.debug_pachelbel_music);
            String musician4 = getResources().getString(R.string.debug_pachelbel_name);
            String tMain4 = getResources().getString(R.string.debug_pachelbel_main);
            String tSub4 = getResources().getString(R.string.debug_pachelbel_sub);
            TemplateDto canon = new TemplateDto(owner4, musicTitle4, musician4, tMain4, tSub4, 20);
            ((TeacherDto) owner4).addTemplate(canon);
            
            /*
                Dummy Template5 : Canon_rock
             */
            UserDto owner5 = joohyun;
            String musicTitle5 = getResources().getString(R.string.debug_canon_rock_music);
            String musician5 = getResources().getString(R.string.debug_canon_rock_name);
            String tMain5 = getResources().getString(R.string.debug_canon_rock_main);
            String tSub5 = getResources().getString(R.string.debug_canon_rock_sub);
            TemplateDto canonRock = new TemplateDto(owner5, musicTitle5, musician5, tMain5, tSub5, 20);
            ((TeacherDto) owner5).addTemplate(canonRock);
            
            /*
                Dummy Template6 : Taryeong
             */
            UserDto owner6 = jeny;
            String musicTitle6 = getResources().getString(R.string.debug_taryeong_music);
            String musician6 = getResources().getString(R.string.debug_taryeong_name);
            String tMain6 = getResources().getString(R.string.debug_taryeong_main);
            String tSub6 = getResources().getString(R.string.debug_taryeong_sub);
            TemplateDto folkSong = new TemplateDto(owner6, musicTitle6, musician6, tMain6, tSub6, 20);
            ((TeacherDto) owner6).addTemplate(folkSong);
            
            /*
                Dummy Template7 : Metallica
             */
            UserDto owner7 = joohyun;
            String musicTitle7 = getResources().getString(R.string.debug_taryeong_music);
            String musician7 = getResources().getString(R.string.debug_taryeong_name);
            String tMain7 = getResources().getString(R.string.debug_taryeong_main);
            String tSub7 = getResources().getString(R.string.debug_taryeong_sub);
            TemplateDto enterTheSandman = new TemplateDto(owner7, musicTitle7, musician7, tMain7, tSub7, 20);
            ((TeacherDto) owner7).addTemplate(enterTheSandman);
            
             /*
                Dummy Template8 : YoonSuckJoon
             */
            UserDto owner8 = jeny;
            String musicTitle8 = getResources().getString(R.string.debug_airplane_music);
            String musician8 = getResources().getString(R.string.debug_airplane_name);
            String tMain8 = getResources().getString(R.string.debug_airplane_main);
            String tSub8 = getResources().getString(R.string.debug_airplane_sub);
            TemplateDto airplain = new TemplateDto(owner8, musicTitle8, musician8, tMain8, tSub8, 20);
            ((TeacherDto) owner8).addTemplate(airplain);
            
             /*
                Dummy Template9 : Chopin
             */
            UserDto owner9 = dahyun;
            String musicTitle9 = getResources().getString(R.string.debug_fantasia_music);
            String musician9 = getResources().getString(R.string.debug_fantasia_name);
            String tMain9 = getResources().getString(R.string.debug_fantasia_main);
            String tSub9 = getResources().getString(R.string.debug_fantasia_sub);
            TemplateDto chopin = new TemplateDto(owner9, musicTitle9, musician9, tMain9, tSub9, 20);
            ((TeacherDto) owner9).addTemplate(chopin);
            
             /*
                Dummy Template10 : Celine dion
             */
            UserDto owner10 = dahyun;
            String musicTitle10 = getResources().getString(R.string.debug_mhwgo_music);
            String musician10 = getResources().getString(R.string.debug_mhwgo_name);
            String tMain10 = getResources().getString(R.string.debug_mhwgo_main);
            String tSub10 = getResources().getString(R.string.debug_mhwgo_sub);
            TemplateDto celineDion = new TemplateDto(owner10, musicTitle10, musician10, tMain10, tSub10, 20);
            ((TeacherDto) owner10).addTemplate(celineDion);
            
            /*
                Dummy Template11 : Mozart
             */
            UserDto owner11 = dahyun;
            String musicTitle11 = getResources().getString(R.string.debug_turkie_music);
            String musician11 = getResources().getString(R.string.debug_turkie_name);
            String tMain11 = getResources().getString(R.string.debug_turkie_main);
            String tSub11 = getResources().getString(R.string.debug_turkie_sub);
            TemplateDto mozart = new TemplateDto(owner11, musicTitle11, musician11, tMain11, tSub11, 21);
            ((TeacherDto) owner11).addTemplate(mozart);
            
             /*
                Dummy Template12 : Vivaldi
             */
            UserDto owner12 = jeny;
            String musicTitle12 = getResources().getString(R.string.debug_vivaldi_music);
            String musician12 = getResources().getString(R.string.debug_vivaldi_name);
            String tMain12 = getResources().getString(R.string.debug_vivaldi_main);
            String tSub12 = getResources().getString(R.string.debug_vivaldi_sub);
            TemplateDto vivaldi = new TemplateDto(owner12, musicTitle12, musician12, tMain12, tSub12, 22);
            ((TeacherDto) owner12).addTemplate(vivaldi);
            
             /*
                Dummy Template13 : RealSanae
             */
            UserDto owner13 = joohyun;
            String musicTitle13 = getResources().getString(R.string.debug_realsanae_music);
            String musician13 = getResources().getString(R.string.debug_realsanae_name);
            String tMain13 = getResources().getString(R.string.debug_realsanae_main);
            String tSub13 = getResources().getString(R.string.debug_realsanae_sub);
            TemplateDto realSanae = new TemplateDto(owner13, musicTitle13, musician13, tMain13, tSub13, 10);
            ((TeacherDto) owner13).addTemplate(realSanae);
            
             /*
                Dummy Template14 : Wap
             */
            UserDto owner14 = joohyun;
            String musicTitle14 = getResources().getString(R.string.debug_wapthehack_music);
            String musician14 = getResources().getString(R.string.debug_wapthehack_name);
            String tMain14 = getResources().getString(R.string.debug_wapthehack_main);
            String tSub14 = getResources().getString(R.string.debug_wapthehack_sub);
            TemplateDto wap = new TemplateDto(owner14, musicTitle14, musician14, tMain14, tSub14, 10);
            ((TeacherDto) owner14).addTemplate(wap);

            /*
                Dummy Notification1 : not1
             */
            boolean trueUser1 = true;
            TemplateDto template1 = moonlight3rd;
            String nMain1 = getResources().getString(R.string.teacher_upload_music);
            String nDate1 = getResources().getString(R.string.debug_aoa_choa_date1);
            NotificationDto not1 = new NotificationDto(trueUser1, template1, nMain1, nDate1);
            
            /*
                Dummy Notification2 : not2
            */
            boolean trueUser2 = false;
            TemplateDto template2 = czernyNo95;
            String nMain2 = getResources().getString(R.string.debug_mi_main);
            String nDate2 = getResources().getString(R.string.debug_mi_date1);
            NotificationDto not2 = new NotificationDto(trueUser2, template2, nMain2, nDate2);
            
            /*
                Dummy Notification3 : not3
            */
            boolean trueUser3 = true;
            TemplateDto template3 = canon;
            String nMain3 = getResources().getString(R.string.friend_complete_music);
            String nDate3 = getResources().getString(R.string.debug_twice_dahyun_date1);
            NotificationDto not3 = new NotificationDto(trueUser3, template3, nMain3, nDate3);

            /*
                Dummy Group1 : Libre
             */
            String gName1 = getResources().getString(R.string.debug_libre_name);
            String gMain1 = getResources().getString(R.string.debug_libre_detail_main);
            String gSub1 = getResources().getString(R.string.debug_libre_detail_sub);
            UserGroupDto libre = new UserGroupDto(gName1, gMain1, gSub1);
            libre.addTeacher((TeacherDto) choa);
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
            UserGroupDto smma = new UserGroupDto(gName2, gMain2, gSub2);
            smma.addTeacher((TeacherDto) dahyun);
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
            UserGroupDto with = new UserGroupDto(gName3, gMain3, gSub3);
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
            UserGroupDto pmc = new UserGroupDto(gName4, gMain4, gSub4);
            pmc.addTeacher((TeacherDto) choa);
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
            UserGroupDto brain = new UserGroupDto(gName5, gMain5, gSub5);
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
            HashMap<String, TemplateDto> templates = new HashMap<>();
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
            ArrayList<NotificationDto> notifications = new ArrayList<>();
            notifications.add(not1);
            notifications.add(not2);
            notifications.add(not3);

            /*
                Insert User Group data
             */
            HashMap<String, UserGroupDto> userGroups = new HashMap<>();
            userGroups.put(libre.getName(), libre);
            userGroups.put(smma.getName(), smma);
            userGroups.put(with.getName(), with);
            userGroups.put(pmc.getName(), pmc);
            userGroups.put(brain.getName(), brain);

            session.setMainUser(mina);
            session.setTemplates(templates);
            session.setNotifications(notifications);
            session.setUserGroups(userGroups);
        }
    }
}
