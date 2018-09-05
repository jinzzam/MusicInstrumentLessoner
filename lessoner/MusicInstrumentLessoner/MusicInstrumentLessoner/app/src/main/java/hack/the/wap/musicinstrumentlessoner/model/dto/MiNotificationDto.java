package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.sql.Timestamp;

public class MiNotificationDto {
    private int miNotificationId;
    private int musicTemplateId;
    private Timestamp registDateTime;
    private String type;
    private String comment;

    public MiNotificationDto(int miNotificationId, int musicTemplateId, Timestamp registDateTime, String type, String comment) {
        this.miNotificationId = miNotificationId;
        this.musicTemplateId = musicTemplateId;
        this.registDateTime = registDateTime;
        this.type = type;
        this.comment = comment;
    }

    public int getMiNotificationId() {
        return miNotificationId;
    }

    public void setMiNotificationId(int miNotificationId) {
        this.miNotificationId = miNotificationId;
    }

    public int getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(int musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public Timestamp getRegistDateTime() {
        return registDateTime;
    }

    public void setRegistDateTime(Timestamp registDateTime) {
        this.registDateTime = registDateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "MiNotificationDto{" +
                "miNotificationId=" + miNotificationId +
                ", musicTemplateId=" + musicTemplateId +
                ", registDateTime=" + registDateTime +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
