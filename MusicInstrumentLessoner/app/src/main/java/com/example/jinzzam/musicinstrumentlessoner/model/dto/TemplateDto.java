package com.example.jinzzam.musicinstrumentlessoner.model.dto;

public class TemplateDto {
    private int musicTemplateId;
    private UserDto owner;
    private String musicTitle;
    private String musician;

    /**
     * create TemplateDto
     *
     * @param musicTemplateId primary_key auto_increment
     * @param owner           not null
     * @param musicTitle      not null
     * @param musician        null이면 작자미상
     */

    public TemplateDto(int musicTemplateId, UserDto owner, String musicTitle, String musician) {
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

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
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
        return "TemplateDto{" +
                "musicTemplateId=" + musicTemplateId +
                ", owner=" + owner +
                ", musicTitle='" + musicTitle + '\'' +
                ", musician='" + musician + '\'' +
                '}';
    }
}
