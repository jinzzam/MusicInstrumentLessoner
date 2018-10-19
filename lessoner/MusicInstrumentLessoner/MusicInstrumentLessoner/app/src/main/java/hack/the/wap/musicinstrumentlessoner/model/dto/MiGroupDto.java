package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MiGroupDto implements Serializable {
    private String groupName;
    private String place;
    private String info;
    private String instruments;
    private String genres;

    public MiGroupDto() {

    }

    /**
     * create MiGroupDto
     *
     * @param groupName
     * @param place
     * @param info
     * @param instruments
     * @param genres
     */

    public MiGroupDto(String groupName, String place, String info, String instruments, String genres) {
        this.groupName = groupName;
        this.place = place;
        this.info = info;
        this.instruments = instruments;
        this.genres = genres;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
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
        return "MiGroupDto{" +
                "groupName='" + groupName + '\'' +
                ", place='" + place + '\'' +
                ", info='" + info + '\'' +
                ", instruments='" + instruments + '\'' +
                ", genres='" + genres + '\'' +
                '}';
    }
}
