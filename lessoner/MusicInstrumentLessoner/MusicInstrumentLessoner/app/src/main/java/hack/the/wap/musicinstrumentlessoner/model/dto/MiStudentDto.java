package hack.the.wap.musicinstrumentlessoner.model.dto;

public class MiStudentDto extends MiUserDto {
    private String studentEmail;
    private String groupName;

    /**
     * create MiStudentDto
     *
     * @param studentEmail
     * @param groupName
     */

    public MiStudentDto(String studentEmail, String groupName) {
        this.studentEmail = studentEmail;
        this.groupName = groupName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "MiStudentDto{" +
                "studentEmail='" + studentEmail + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
