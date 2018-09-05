package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.util.HashMap;

public class TeacherDto {
    private String groupName; //join 할 때 쓰이는 거
    private String TEmail; //user table 에 의해 fk

    public TeacherDto(String groupName, String TEmail) {
        this.groupName = groupName;
        this.TEmail = TEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTEmail() {
        return TEmail;
    }

    public void setTEmail(String TEmail) {
        this.TEmail = TEmail;
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "groupName='" + groupName + '\'' +
                ", TEmail='" + TEmail + '\'' +
                '}';
    }
}
