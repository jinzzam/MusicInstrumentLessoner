package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.sql.Time;

public class MusicTemplateGuideDto {
    private int musicTemplateId;
    private Time playTime;
    private String comment;

    /**
     * create MusicTemplateGuideDto
     *
     * @param musicTemplateId
     * @param playTime
     * @param comment
     */

    public MusicTemplateGuideDto(int musicTemplateId, Time playTime, String comment) {
        this.musicTemplateId = musicTemplateId;
        this.playTime = playTime;
        this.comment = comment;
    }

    public int getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(int musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public Time getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Time playTime) {
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
        return "MusicTemplateGuideDto{" +
                "musicTemplateId=" + musicTemplateId +
                ", playTime=" + playTime +
                ", comment='" + comment + '\'' +
                '}';
    }
}
