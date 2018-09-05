package hack.the.wap.musicinstrumentlessoner.model.dto;

public class StudentDto {

    private String groupName;
    private String SEmail;

    public StudentDto(String groupName, String SEmail) {
        this.groupName = groupName;
        this.SEmail = SEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSEmail() {
        return SEmail;
    }

    public void setSEmail(String SEmail) {
        this.SEmail = SEmail;
    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "groupName='" + groupName + '\'' +
                ", SEmail='" + SEmail + '\'' +
                '}';
    }
}
