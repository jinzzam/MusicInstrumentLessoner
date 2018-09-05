package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class UserGroupDto implements Serializable {
    private String name;
    private String info;
    private String instruments;
    private String genres;

    public UserGroupDto(String name, String info, String instruments, String genres) {
        this.name = name;
        this.info = info;
        this.instruments = instruments;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInstruments() {
        return instruments;
    }

    public void setInstruments(String instruments) {
        this.instruments = instruments;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "UserGroupDto{" +
                "name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", instruments='" + instruments + '\'' +
                ", genres='" + genres + '\'' +
                '}';
    }
}
