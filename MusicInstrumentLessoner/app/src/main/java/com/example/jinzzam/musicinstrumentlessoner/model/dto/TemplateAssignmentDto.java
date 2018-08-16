package com.example.jinzzam.musicinstrumentlessoner.model.dto;

public class TemplateAssignmentDto {
    private String innerFilename;
    private TemplateDto musicTemplateId;

    /**
     * create TemplateAssignmentDto
     *
     * @param innerFilename   PK
     * @param musicTemplateId FK
     */

    public TemplateAssignmentDto(String innerFilename, TemplateDto musicTemplateId) {
        this.innerFilename = innerFilename;
        this.musicTemplateId = musicTemplateId;
    }

    public String getInnerFilename() {
        return innerFilename;
    }

    public void setInnerFilename(String innerFilename) {
        this.innerFilename = innerFilename;
    }

    public TemplateDto getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(TemplateDto musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    @Override
    public String toString() {
        return "TemplateAssignmentDto{" +
                "innerFilename='" + innerFilename + '\'' +
                ", musicTemplateId=" + musicTemplateId +
                '}';
    }
}
