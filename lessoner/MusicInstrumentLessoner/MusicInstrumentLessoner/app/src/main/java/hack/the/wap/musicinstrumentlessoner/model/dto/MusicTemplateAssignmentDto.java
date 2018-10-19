package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;

public class MusicTemplateAssignmentDto implements Serializable {
    private String innerFilename;
    private int musicTemplateId;
    private String studentEmail;
    private int toDoCount;
    private int doneCount;
    private int successPercent;

    public MusicTemplateAssignmentDto() {

    }

    /**
     * create MusicTemplateAssignmentDto
     *
     * @param innerFilename
     * @param musicTemplateId
     * @param studentEmail
     * @param toDoCount
     * @param doneCount
     * @param successPercent
     */

    public MusicTemplateAssignmentDto(String innerFilename, int musicTemplateId, String studentEmail, int toDoCount, int doneCount, int successPercent) {
        this.innerFilename = innerFilename;
        this.musicTemplateId = musicTemplateId;
        this.studentEmail = studentEmail;
        this.toDoCount = toDoCount;
        this.doneCount = doneCount;
        this.successPercent = successPercent;
    }

    public String getInnerFilename() {
        return innerFilename;
    }

    public void setInnerFilename(String innerFilename) {
        this.innerFilename = innerFilename;
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

    public int getToDoCount() {
        return toDoCount;
    }

    public void setToDoCount(int toDoCount) {
        this.toDoCount = toDoCount;
    }

    public int getDoneCount() {
        return doneCount;
    }

    public void setDoneCount(int doneCount) {
        this.doneCount = doneCount;
    }

    public int getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(int successPercent) {
        this.successPercent = successPercent;
    }

    @Override
    public String toString() {
        return "MusicTemplateAssignmentDto{" +
                "innerFilename='" + innerFilename + '\'' +
                ", musicTemplateId=" + musicTemplateId +
                ", studentEmail='" + studentEmail + '\'' +
                ", toDoCount=" + toDoCount +
                ", doneCount=" + doneCount +
                ", successPercent=" + successPercent +
                '}';
    }
}
