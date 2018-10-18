package hack.the.wap.musicinstrumentlessoner.session;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiFileDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiStudentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateGuideDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplatePracticeDto;

public class Session {
    private static Session instance;
    private MiUserDto mainUser;
    private HashMap<String, MiUserDto> users;
    private ArrayList<MiNotificationDto> notifications;
    private HashMap<String, MiFileDto> files;
    private HashMap<String, MusicTemplateDto> templates;
    private HashMap<String, MusicTemplateGuideDto> templateGuides;
    private HashMap<String, MusicTemplateAssignmentDto> templateAssignments;
    private ArrayList<MusicTemplatePracticeDto> templatePractices;
    private HashMap<String, MiGroupDto> userGroups;
    private ArrayList<MiTeacherDto> groupTeachers;
    private ArrayList<MiStudentDto> groupStudents;

    private Session() {
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

    public Session(MiUserDto mainUser, HashMap<String, MiUserDto> users, ArrayList<MiNotificationDto> notifications, HashMap<String, MiFileDto> files, HashMap<String, MusicTemplateDto> templates, HashMap<String, MusicTemplateGuideDto> templateGuides, HashMap<String, MusicTemplateAssignmentDto> templateAssignments, ArrayList<MusicTemplatePracticeDto> templatePractices, HashMap<String, MiGroupDto> userGroups, ArrayList<MiTeacherDto> groupTeachers, ArrayList<MiStudentDto> groupStudents) {
        this.mainUser = mainUser;
        this.users = users;
        this.notifications = notifications;
        this.files = files;
        this.templates = templates;
        this.templateGuides = templateGuides;
        this.templateAssignments = templateAssignments;
        this.templatePractices = templatePractices;
        this.userGroups = userGroups;
        this.groupTeachers = groupTeachers;
        this.groupStudents = groupStudents;
    }

    public static void setInstance(Session instance) {
        Session.instance = instance;
    }

    public MiUserDto getMainUser() {
        return mainUser;
    }

    public void setMainUser(MiUserDto mainUser) {
        this.mainUser = mainUser;
    }

    public HashMap<String, MiUserDto> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, MiUserDto> users) {
        this.users = users;
    }

    public ArrayList<MiNotificationDto> getNotifications() {
        return notifications;
    }

    public void setNotifications(ArrayList<MiNotificationDto> notifications) {
        this.notifications = notifications;
    }

    public HashMap<String, MiFileDto> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, MiFileDto> files) {
        this.files = files;
    }

    public HashMap<String, MusicTemplateDto> getTemplates() {
        return templates;
    }

    public void setTemplates(HashMap<String, MusicTemplateDto> templates) {
        this.templates = templates;
    }

    public HashMap<String, MusicTemplateGuideDto> getTemplateGuides() {
        return templateGuides;
    }

    public void setTemplateGuides(HashMap<String, MusicTemplateGuideDto> templateGuides) {
        this.templateGuides = templateGuides;
    }

    public HashMap<String, MusicTemplateAssignmentDto> getTemplateAssignments() {
        return templateAssignments;
    }

    public void setTemplateAssignments(HashMap<String, MusicTemplateAssignmentDto> templateAssignments) {
        this.templateAssignments = templateAssignments;
    }

    public ArrayList<MusicTemplatePracticeDto> getTemplatePractices() {
        return templatePractices;
    }

    public void setTemplatePractices(ArrayList<MusicTemplatePracticeDto> templatePractices) {
        this.templatePractices = templatePractices;
    }

    public HashMap<String, MiGroupDto> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(HashMap<String, MiGroupDto> userGroups) {
        this.userGroups = userGroups;
    }

    public ArrayList<MiTeacherDto> getGroupTeachers() {
        return groupTeachers;
    }

    public void setGroupTeachers(ArrayList<MiTeacherDto> groupTeachers) {
        this.groupTeachers = groupTeachers;
    }

    public ArrayList<MiStudentDto> getGroupStudents() {
        return groupStudents;
    }

    public void setGroupStudents(ArrayList<MiStudentDto> groupStudents) {
        this.groupStudents = groupStudents;
    }

    @Override
    public String toString() {
        return "Session{" +
                "mainUser=" + mainUser +
                ", users=" + users +
                ", notifications=" + notifications +
                ", files=" + files +
                ", templates=" + templates +
                ", templateGuides=" + templateGuides +
                ", templateAssignments=" + templateAssignments +
                ", templatePractices=" + templatePractices +
                ", userGroups=" + userGroups +
                ", groupTeachers=" + groupTeachers +
                ", groupStudents=" + groupStudents +
                '}';
    }
}
