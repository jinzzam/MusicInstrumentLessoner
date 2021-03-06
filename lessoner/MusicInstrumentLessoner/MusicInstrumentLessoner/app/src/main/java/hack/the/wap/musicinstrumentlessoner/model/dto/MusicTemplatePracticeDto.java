package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;

public class MusicTemplatePracticeDto implements Serializable{
    private int musicTemplatePracticeId;
    private int musicTemplateId;
    private String studentEmail;
    private String innerFilename;
    private int isDone;
    private int completePercent;

    public MusicTemplatePracticeDto() {

    }

    /**
     * create MusicTemplatePracticeDto
     *
     * @param musicTemplatePracticeId
     * @param musicTemplateId
     * @param studentEmail
     * @param innerFilename
     * @param isDone
     * @param completePercent
     */

    public MusicTemplatePracticeDto(int musicTemplatePracticeId, int musicTemplateId, String studentEmail, String innerFilename, int isDone, int completePercent) {
        this.musicTemplatePracticeId = musicTemplatePracticeId;
        this.musicTemplateId = musicTemplateId;
        this.studentEmail = studentEmail;
        this.innerFilename = innerFilename;
        this.isDone = isDone;
        this.completePercent = completePercent;
    }

    public int getMusicTemplatePracticeId() {
        return musicTemplatePracticeId;
    }

    public void setMusicTemplatePracticeId(int musicTemplatePracticeId) {
        this.musicTemplatePracticeId = musicTemplatePracticeId;
    }

    public int getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(int musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getInnerFilename() {
        return innerFilename;
    }

    public void setInnerFilename(String innerFilename) {
        this.innerFilename = innerFilename;
    }

    public int isDone() {
        return isDone;
    }

    public void setDone(int done) {
        isDone = done;
    }

    public int getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(int completePercent) {
        this.completePercent = completePercent;
    }

    @Override
    public String toString() {
        return "MusicTemplatePracticeDto{" +
                "musicTemplatePracticeId=" + musicTemplatePracticeId +
                ", musicTemplateId=" + musicTemplateId +
                ", studentEmail='" + studentEmail + '\'' +
                ", innerFilename='" + innerFilename + '\'' +
                ", isDone=" + isDone +
                ", completePercent=" + completePercent +
                '}';
    }
}
