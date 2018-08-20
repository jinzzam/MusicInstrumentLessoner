package com.example.jinzzam.musicinstrumentlessoner.model.dto;

public class FileDto {
    private String innerFilename;
    private String outterFilename;
    private UserDto owner;

    /**
     * create FileDto
     *
     * @param innerFilename  PK
     * @param outterFilename
     * @param owner          FK
     */

    public FileDto(String innerFilename, String outterFilename, UserDto owner) {
        this.innerFilename = innerFilename;
        this.outterFilename = outterFilename;
        this.owner = owner;
    }

    public String getInnerFilename() {
        return innerFilename;
    }

    public void setInnerFilename(String innerFilename) {
        this.innerFilename = innerFilename;
    }

    public String getOutterFilename() {
        return outterFilename;
    }

    public void setOutterFilename(String outterFilename) {
        this.outterFilename = outterFilename;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "FileDto{" +
                "innerFilename='" + innerFilename + '\'' +
                ", outterFilename='" + outterFilename + '\'' +
                ", owner=" + owner +
                '}';
    }
}
