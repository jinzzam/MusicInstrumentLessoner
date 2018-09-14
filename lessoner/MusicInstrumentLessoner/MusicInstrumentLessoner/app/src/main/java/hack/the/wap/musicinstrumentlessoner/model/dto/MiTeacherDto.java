package hack.the.wap.musicinstrumentlessoner.model.dto;

public class MiTeacherDto extends MiUserDto {
    private String teacherEmail; //user table 에 의해 fk
    private String groupName; //join 할 때 쓰이는 거

    /**
     * create MiTeacherDto
     *
     * @param teacherEmail
     * @param groupName
     */

    public MiTeacherDto(String teacherEmail, String groupName) {
        this.teacherEmail = teacherEmail;
        this.groupName = groupName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "MiTeacherDto{" +
                "teacherEmail='" + teacherEmail + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
