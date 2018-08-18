package com.example.jinzzam.musicinstrumentlessoner.model.dto;

public class GruopDto {
    private String groupName;
    private String groupInfo;
    private boolean isTeacher;

    public GruopDto(String groupName, String groupInfo, boolean isTeacher) {
        this.groupName = groupName;
        this.groupInfo = groupInfo;
        this.isTeacher = isTeacher;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(String groupInfo) {
        this.groupInfo = groupInfo;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    @Override
    public String toString() {
        return "GruopDto{" +
                "groupName='" + groupName + '\'' +
                ", groupInfo='" + groupInfo + '\'' +
                ", isTeacher=" + isTeacher +
                '}';
    }
}
