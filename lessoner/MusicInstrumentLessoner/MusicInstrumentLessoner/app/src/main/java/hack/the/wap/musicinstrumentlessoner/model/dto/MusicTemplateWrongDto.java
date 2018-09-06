package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.sql.Time;

public class MusicTemplateWrongDto {
    private String innerFilename;
    private Time wrongTimeStart;
    private Time wrongTimeEnd;

    /**
     * create MusicTemplateWrongDto
     *
     * @param innerFilename
     * @param wrongTimeStart
     * @param wrongTimeEnd
     */

    public MusicTemplateWrongDto(String innerFilename, Time wrongTimeStart, Time wrongTimeEnd) {
        this.innerFilename = innerFilename;
        this.wrongTimeStart = wrongTimeStart;
        this.wrongTimeEnd = wrongTimeEnd;
    }

    public String getInnerFilename() {
        return innerFilename;
    }

    public void setInnerFilename(String innerFilename) {
        this.innerFilename = innerFilename;
    }

    public Time getWrongTimeStart() {
        return wrongTimeStart;
    }

    public void setWrongTimeStart(Time wrongTimeStart) {
        this.wrongTimeStart = wrongTimeStart;
    }

    public Time getWrongTimeEnd() {
        return wrongTimeEnd;
    }

    public void setWrongTimeEnd(Time wrongTimeEnd) {
        this.wrongTimeEnd = wrongTimeEnd;
    }

    @Override
    public String toString() {
        return "MusicTemplateWrongDto{" +
                "innerFilename='" + innerFilename + '\'' +
                ", wrongTimeStart=" + wrongTimeStart +
                ", wrongTimeEnd=" + wrongTimeEnd +
                '}';
    }
}
