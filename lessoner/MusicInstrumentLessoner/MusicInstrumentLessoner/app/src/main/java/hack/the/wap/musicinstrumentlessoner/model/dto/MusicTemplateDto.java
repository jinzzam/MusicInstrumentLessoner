package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;

public class MusicTemplateDto implements Serializable {
    private int musicTemplateId;
    private String owner;
    private String musicTitle;
    private String musician;
    private String guide;


    public MusicTemplateDto() {

    }

    /**
     * create MusicTemplateDto
     *
     * @param musicTemplateId
     * @param owner
     * @param musicTitle
     * @param musician
     * @param guide
     */

    public MusicTemplateDto(int musicTemplateId, String owner, String musicTitle, String musician, String guide) {
        this.musicTemplateId = musicTemplateId;
        this.owner = owner;
        this.musicTitle = musicTitle;
        this.musician = musician;
        this.guide = guide;
    }

    public int getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(int musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    @Override
    public String toString() {
        return "MusicTemplateDto{" +
                "musicTemplateId=" + musicTemplateId +
                ", owner='" + owner + '\'' +
                ", musicTitle='" + musicTitle + '\'' +
                ", musician='" + musician + '\'' +
                ", guide='" + guide + '\'' +
                '}';
    }
}
