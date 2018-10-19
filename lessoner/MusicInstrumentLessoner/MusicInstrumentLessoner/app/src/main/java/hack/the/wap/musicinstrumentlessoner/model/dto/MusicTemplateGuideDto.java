package hack.the.wap.musicinstrumentlessoner.model.dto;


public class MusicTemplateGuideDto {
    private int musicTemplateId;
    private String playTime;
    private String comment;

    public MusicTemplateGuideDto() {

    }

    /**
     * create MusicTemplateGuideDto
     *
     * @param musicTemplateId
     * @param playTime
     * @param comment
     */

    public MusicTemplateGuideDto(int musicTemplateId, String playTime, String comment) {
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

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
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
