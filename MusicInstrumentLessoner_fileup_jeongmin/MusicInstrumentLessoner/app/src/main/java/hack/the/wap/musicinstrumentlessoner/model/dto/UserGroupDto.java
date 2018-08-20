package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserGroupDto implements Serializable{
    private boolean mine;
    private String name;
    private String main;
    private String sub;
    private HashMap<String, TeacherDto> teachers;
    private HashMap<String, UserDto> users;
    private HashMap<String, String> instruments;
    private HashMap<String, String> genres;

    {
        mine = false;
    }

    public UserGroupDto(String name) {
        this.name = name;
        users = new HashMap<>();
        teachers = new HashMap<>();
        instruments = new HashMap<>();
        genres = new HashMap<>();
    }

    public UserGroupDto(String name, String main, String sub) {
        this.name = name;
        this.main = main;
        this.sub = sub;
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
        teachers.put(dto.getName(), dto);
    }

    public void addUser(UserDto dto) {
        users.put(dto.getName(), dto);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
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

    public void addInstrument(String instrument){
        this.instruments.put(instrument, instrument);
    }

    public void addGenre(String genre){
        this.genres.put(genre, genre);
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    @Override
    public String toString() {
        return "UserGroupDto{" +
                "name='" + name + '\'' +
                ", main='" + main + '\'' +
                ", sub='" + sub + '\'' +
                ", teachers=" + teachers +
                ", users=" + users +
                ", instruments=" + instruments +
                ", genres=" + genres +
                '}';
    }
}
