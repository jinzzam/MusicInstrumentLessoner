package hack.the.wap.musicinstrumentlessoner.session;

import android.os.Debug;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.debug.DebugMode;
import hack.the.wap.musicinstrumentlessoner.model.dto.NotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.TemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.UserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.UserGroupDto;

public class Session {
    private static Session instance;
    private UserDto mainUser;
    private ArrayList<NotificationDto> notifications;
    private HashMap<String, TemplateDto> templates;
    private HashMap<String, UserGroupDto> userGroups;

    private Session() {
    }

    public UserDto getMainUser() {
        return mainUser;
    }

    public void setMainUser(UserDto mainUser) {
        this.mainUser = mainUser;
    }

    public ArrayList<NotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<NotificationDto> notifications) {
        this.notifications = notifications;
    }

    public HashMap<String, TemplateDto> getTemplates() {
        return templates;
    }

    public void setTemplates(HashMap<String, TemplateDto> templates) {
        this.templates = templates;
    }

    public HashMap<String, UserGroupDto> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(HashMap<String, UserGroupDto> userGroups) {
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
