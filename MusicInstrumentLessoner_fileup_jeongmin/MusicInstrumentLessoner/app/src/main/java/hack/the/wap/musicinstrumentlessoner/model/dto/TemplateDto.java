package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class TemplateDto implements Serializable {
    private final static int DEFAULT_PRACTICE_SIZE = 10;
    private UserDto owner;
    private String musicTitle;
    private String musician;
    private String main;
    private String sub;
    private GuideDto guide;
    private ArrayList<TemplatePracticeDto> templatePractices;

    /**
     * Create Template
     *
     * @param owner
     * @param musicTitle
     * @param musician
     * @param main
     * @param sub
     */
    public TemplateDto(UserDto owner, String musicTitle, String musician, String main, String sub) {
        this.owner = owner;
        this.musicTitle = musicTitle;
        this.musician = musician;
        this.main = main;
        this.sub = sub;
        templatePractices = new ArrayList<>();
        for (int i = 0; i < DEFAULT_PRACTICE_SIZE; i++) {
            templatePractices.add(new TemplatePracticeDto(i + 1, null));
        }
    }

    /**
     * Create Template
     *
     * @param owner
     * @param musicTitle
     * @param musician
     * @param main
     * @param sub
     * @param guide
     */
    public TemplateDto(UserDto owner, String musicTitle, String musician, String main, String sub, GuideDto guide) {
        this.owner = owner;
        this.musicTitle = musicTitle;
        this.musician = musician;
        this.main = main;
        this.sub = sub;
        this.guide = guide;
        templatePractices = new ArrayList<>();
        for (int i = 0; i < DEFAULT_PRACTICE_SIZE; i++) {
            templatePractices.add(new TemplatePracticeDto(i + 1, null));
        }
    }

    /**
     * Create Template
     *
     * @param owner
     * @param musicTitle
     * @param musician
     * @param main
     * @param sub
     */
    public TemplateDto(UserDto owner, String musicTitle, String musician, String main, String sub, int practiceCount) {
        this(owner, musicTitle, musician, main, sub);
        templatePractices = new ArrayList<>();
        for (int i = 0; i < practiceCount; i++) {
            templatePractices.add(new TemplatePracticeDto(i + 1, null));
        }
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public void setMusicTitle(String musicTitle) {
        this.musicTitle = musicTitle;
    }

    public String getMusician() {
        return musician;
    }

    public void setMusician(String musician) {
        this.musician = musician;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public ArrayList<TemplatePracticeDto> getTemplatePractices() {
        return templatePractices;
    }

    public GuideDto getGuide() {
        return guide;
    }

    public void setGuide(GuideDto guide) {
        this.guide = guide;
    }

    public void setTemplatePractices(ArrayList<TemplatePracticeDto> templatePractices) {
        this.templatePractices = templatePractices;
    }

    @Override
    public String toString() {
        return "TemplateDto{" +
                "owner=" + owner +
                ", musicTitle='" + musicTitle + '\'' +
                ", musician='" + musician + '\'' +
                ", main='" + main + '\'' +
                ", sub='" + sub + '\'' +
                ", templatePractices=" + templatePractices +
                '}';
    }
}
