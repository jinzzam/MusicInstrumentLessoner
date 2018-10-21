package hack.the.wap.musicinstrumentlessoner.model.dto;


public class MusicTemplateWrongDto {
    private int musicTemplateId;
    private int musicTemplatePracticeId;
    private String studentEmail;
    private String wrongTimeStart;
    private String wrongTimeEnd;
    private String comment;

    public MusicTemplateWrongDto(int musicTemplateId, int musicTemplatePracticeId, String studentEmail, String wrongTimeStart, String wrongTimeEnd, String comment) {
        this.musicTemplateId = musicTemplateId;
        this.musicTemplatePracticeId = musicTemplatePracticeId;
        this.studentEmail = studentEmail;
        this.wrongTimeStart = wrongTimeStart;
        this.wrongTimeEnd = wrongTimeEnd;
        this.comment = comment;
    }

    public int getMusicTemplateId() {
        return musicTemplateId;
    }

    public void setMusicTemplateId(int musicTemplateId) {
        this.musicTemplateId = musicTemplateId;
    }

    public int getMusicTemplatePracticeId() {
        return musicTemplatePracticeId;
    }

    public void setMusicTemplatePracticeId(int musicTemplatePracticeId) {
        this.musicTemplatePracticeId = musicTemplatePracticeId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getWrongTimeStart() {
        return wrongTimeStart;
    }

    public void setWrongTimeStart(String wrongTimeStart) {
        this.wrongTimeStart = wrongTimeStart;
    }

    public String getWrongTimeEnd() {
        return wrongTimeEnd;
    }

    public void setWrongTimeEnd(String wrongTimeEnd) {
        this.wrongTimeEnd = wrongTimeEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "MusicTemplateWrongDto{" +
                "musicTemplateId=" + musicTemplateId +
                ", musicTemplatePracticeId=" + musicTemplatePracticeId +
                ", studentEmail='" + studentEmail + '\'' +
                ", wrongTimeStart='" + wrongTimeStart + '\'' +
                ", wrongTimeEnd='" + wrongTimeEnd + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
