package com.example.jinzzam.musicinstrumentlessoner.model.dto;

import java.util.Date;

public class NotificationDto {
    private int notificationId;
    private TemplateDto musicTemplateId;
    private Date registDateTime;
    private String type;
    private String comment;

    /**
     * create NotificationDto
     *
     * @param notificationId  PK
     * @param musicTemplateId FK
     * @param registDateTime  current_timestamp
     * @param type
     * @param comment
     */

    public NotificationDto(int notificationId, TemplateDto musicTemplateId, Date registDateTime, String type, String comment) {
        this.notificationId = notificationId;
        this.musicTemplateId = musicTemplateId;
        this.registDateTime = registDateTime;
        this.type = type;
        this.comment = comment;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public TemplateDto getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(TemplateDto musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public Date getRegistDateTime() {
        return registDateTime;
    }

    public void setRegistDateTime(Date registDateTime) {
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
        return "NotificationDto{" +
                "notificationId=" + notificationId +
                ", musicTemplateId=" + musicTemplateId +
                ", registDateTime=" + registDateTime +
                ", type='" + type + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
