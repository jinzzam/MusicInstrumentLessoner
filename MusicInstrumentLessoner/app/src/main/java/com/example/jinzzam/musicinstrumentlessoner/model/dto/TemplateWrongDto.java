package com.example.jinzzam.musicinstrumentlessoner.model.dto;

import java.sql.Time;

public class TemplateWrongDto {
    private String assignmentInnerFilename;
    private Time playTime;

    /**
     * create TemplateWrongDto
     *
     * @param assignmentInnerFilename PK
     * @param playTime                PK
     */

    public TemplateWrongDto(String assignmentInnerFilename, Time playTime) {
        this.assignmentInnerFilename = assignmentInnerFilename;
        this.playTime = playTime;
    }

    public String getAssignmentInnerFilename() {
        return assignmentInnerFilename;
    }

    public void setAssignmentInnerFilename(String assignmentInnerFilename) {
        this.assignmentInnerFilename = assignmentInnerFilename;
    }

    public Time getPlayTime() {
        return playTime;
    }

    public void setPlayTime(Time playTime) {
        this.playTime = playTime;
    }

    @Override
    public String toString() {
        return "TemplateWrongDto{" +
                "assignmentInnerFilename='" + assignmentInnerFilename + '\'' +
                ", playTime=" + playTime +
                '}';
    }
}
