package hack.the.wap.musicinstrumentlessoner.model.dto;

public class MiFileDto {
    private String owner;
    private String innerFilename;
    private String outterFilename;

    public MiFileDto(String owner, String innerFilename, String outterFilename) {
        this.owner = owner;
        this.innerFilename = innerFilename;
        this.outterFilename = outterFilename;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
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

    @Override
    public String toString() {
        return "MiFileDto{" +
                "owner='" + owner + '\'' +
                ", innerFilename='" + innerFilename + '\'' +
                ", outterFilename='" + outterFilename + '\'' +
                '}';
    }
}
