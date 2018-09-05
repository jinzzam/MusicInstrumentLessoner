package hack.the.wap.musicinstrumentlessoner.model.dto;

public class MusicTemplateAssignmentDto {
    private String innerFilename;
    private int musicTemplateId;

    /**
     * create MusicTemplateAssignmentDto
     *
     * @param innerFilename
     * @param musicTemplateId
     */

    public MusicTemplateAssignmentDto(String innerFilename, int musicTemplateId) {
        this.innerFilename = innerFilename;
        this.musicTemplateId = musicTemplateId;
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

    @Override
    public String toString() {
        return "MusicTemplateAssignmentDto{" +
                "innerFilename='" + innerFilename + '\'' +
                ", musicTemplateId=" + musicTemplateId +
                '}';
    }
}
