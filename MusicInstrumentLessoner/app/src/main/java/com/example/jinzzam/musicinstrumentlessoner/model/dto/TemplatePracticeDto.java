package com.example.jinzzam.musicinstrumentlessoner.model.dto;

public class TemplatePracticeDto {
    private int musicTemplatePracticeId;
    private TemplateDto musicTemplateId;
    private UserDto studentEmail;
    private FileDto innerFilename;

    /**
     * create TemplatePracticeDto
     *
     * @param musicTemplatePracticeId PK auto_increment
     * @param musicTemplateId         FK
     * @param studentEmail            FK
     * @param innerFilename           FK
     */

    public TemplatePracticeDto(int musicTemplatePracticeId, TemplateDto musicTemplateId, UserDto studentEmail, FileDto innerFilename) {
        this.musicTemplatePracticeId = musicTemplatePracticeId;
        this.musicTemplateId = musicTemplateId;
        this.studentEmail = studentEmail;
        this.innerFilename = innerFilename;
    }

    public int getMusicTemplatePracticeId() {
        return musicTemplatePracticeId;
    }

    public void setMusicTemplatePracticeId(int musicTemplatePracticeId) {
        this.musicTemplatePracticeId = musicTemplatePracticeId;
    }

    public TemplateDto getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(TemplateDto musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public UserDto getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(UserDto studentEmail) {
        this.studentEmail = studentEmail;
    }

    public FileDto getInnerFilename() {
        return innerFilename;
    }

    public void setInnerFilename(FileDto innerFilename) {
        this.innerFilename = innerFilename;
    }

    @Override
    public String toString() {
        return "TemplatePracticeDto{" +
                "musicTemplatePracticeId=" + musicTemplatePracticeId +
                ", musicTemplateId=" + musicTemplateId +
                ", studentEmail=" + studentEmail +
                ", innerFilename=" + innerFilename +
                '}';
    }
}
