package com.example.jinzzam.musicinstrumentlessoner.model.dto;

import java.io.Serializable;
import java.util.HashMap;

public class GruopDto implements Serializable {
    private String groupName;
    private String groupInfo;
    private boolean isTeacher;
    private HashMap<String, TeacherDto> teachers;
    private HashMap<String, UserDto> users;
    private HashMap<String, String> instruments;
    private HashMap<String, String> genres;

    {
        isTeacher = false;
    }

    public GruopDto(String groupName) {
        this.groupName = groupName;
        users = new HashMap<>();
        teachers = new HashMap<>();
        instruments = new HashMap<>();
        genres = new HashMap<>();
    }

    public HashMap<String, UserDto> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, UserDto> users) {
        this.users = users;
    }

    public HashMap<String, TeacherDto> getTeachers() {
        return teachers;
    }

    public void setTeachers(HashMap<String, TeacherDto> teachers) {
        this.teachers = teachers;
    }

    public void addTeacher(TeacherDto dto) {
        teachers.put(dto.getUsername(), dto);
    }

    public void addUser(UserDto dto) {
        users.put(dto.getUsername(), dto);
    }

    public String getName() {
        return groupName;
    }

    public void setName(String name) {
        this.groupName = name;
    }

    public HashMap<String, String> getInstrument() {
        return instruments;
    }

    public void setInstrument(HashMap<String, String> instruments) {
        this.instruments = instruments;
    }

    public HashMap<String, String> getGenre() {
        return genres;
    }

    public void setGenre(HashMap<String, String> genres) {
        this.genres = genres;
    }

    public void addInstrument(String instrument) {
        this.instruments.put(instrument, instrument);
    }

    public void addGenre(String genre) {
        this.genres.put(genre, genre);
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    @Override
    public String toString() {
        return "GruopDto{" +
                "groupName='" + groupName + '\'' +
                ", groupInfo='" + groupInfo + '\'' +
                ", isTeacher=" + isTeacher +
                ", teachers=" + teachers +
                ", users=" + users +
                ", instruments=" + instruments +
                ", genres=" + genres +
                '}';
    }
}
