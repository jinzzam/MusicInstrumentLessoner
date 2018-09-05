package hack.the.wap.musicinstrumentlessoner.model.dto;

public class MusicTemplatePracticeDto {
    private int musicTemplatePracticeId;
    private int musicTemplateId;
    private String studentEmail;
    private String innerFilename;

    /**
     * create MusicTemplatePracticeDto
     *
     * @param musicTemplatePracticeId
     * @param musicTemplateId
     * @param studentEmail
     * @param innerFilename
     */

    public MusicTemplatePracticeDto(int musicTemplatePracticeId, int musicTemplateId, String studentEmail, String innerFilename) {
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

    @Override
    public String toString() {
        return "MusicTemplatePracticeDto{" +
                "musicTemplatePracticeId=" + musicTemplatePracticeId +
                ", musicTemplateId=" + musicTemplateId +
                ", studentEmail='" + studentEmail + '\'' +
                ", innerFilename='" + innerFilename + '\'' +
                '}';
    }
}
