package com.example.jinzzam.musicinstrumentlessoner.model.dto;

public class TemplateGuideDto {
    private TemplateDto musicTemplateId;
    private int playTime;
    private String comment;

    /**
     * create TemplateGuideDto
     *
     * @param musicTemplateId PK FK
     * @param playTime        PK
     * @param comment
     */

    public TemplateGuideDto(TemplateDto musicTemplateId, int playTime, String comment) {
        this.musicTemplateId = musicTemplateId;
        this.playTime = playTime;
        this.comment = comment;
    }

    public TemplateDto getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(TemplateDto musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "TemplateGuideDto{" +
                "musicTemplateId=" + musicTemplateId +
                ", playTime=" + playTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
