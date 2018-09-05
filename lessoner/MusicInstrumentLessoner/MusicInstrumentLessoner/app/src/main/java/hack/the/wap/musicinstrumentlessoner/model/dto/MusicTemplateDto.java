package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;

public class MusicTemplateDto implements Serializable {
    private int musicTemplateId;
    private String owner;
    private String musicTitle;
    private String musician;

    /**
     * create MusicTemplateDto
     *
     * @param musicTemplateId
     * @param owner
     * @param musicTitle
     * @param musician
     */

    public MusicTemplateDto(int musicTemplateId, String owner, String musicTitle, String musician) {
        this.musicTemplateId = musicTemplateId;
        this.owner = owner;
        this.musicTitle = musicTitle;
        this.musician = musician;
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

    @Override
    public String toString() {
        return "MusicTemplateDto{" +
                "musicTemplateId=" + musicTemplateId +
                ", owner='" + owner + '\'' +
                ", musicTitle='" + musicTitle + '\'' +
                ", musician='" + musician + '\'' +
                '}';
    }
}
