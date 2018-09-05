package hack.the.wap.musicinstrumentlessoner.session;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;

public class Session {
    private static Session instance;
    private MiUserDto mainUser;
    private ArrayList<MiNotificationDto> notifications;
    private HashMap<String, MusicTemplateDto> templates;
    private HashMap<String, MiGroupDto> userGroups;

    private Session() {
    }

    public MiUserDto getMainUser() {
        return mainUser;
    }

    public void setMainUser(MiUserDto mainUser) {
        this.mainUser = mainUser;
    }

    public ArrayList<MiNotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<MiNotificationDto> notifications) {
        this.notifications = notifications;
    }

    public HashMap<String, MusicTemplateDto> getTemplates() {
        return templates;
    }

    public void setTemplates(HashMap<String, MusicTemplateDto> templates) {
        this.templates = templates;
    }

    public HashMap<String, MiGroupDto> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(HashMap<String, MiGroupDto> userGroups) {
        this.userGroups = userGroups;
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public void showAllSession() {
        Log.e("Session", ">>>" + mainUser);
        Log.e("Session", ">>>" + notifications);
        Log.e("Session", ">>>" + templates);
        Log.e("Session", ">>>" + userGroups);

    }
}
