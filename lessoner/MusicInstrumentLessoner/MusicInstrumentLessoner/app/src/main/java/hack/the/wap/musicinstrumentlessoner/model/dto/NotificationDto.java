package hack.the.wap.musicinstrumentlessoner.model.dto;

public class NotificationDto {
    private boolean trueUser;
    private TemplateDto template;
    private String main;
    private String date;

    /**
     * Create Notification
     * @param trueUser
     * @param template
     * @param main
     * @param date
     */
    public NotificationDto(boolean trueUser, TemplateDto template, String main, String date) {
        this.trueUser = trueUser;
        this.template = template;
        this.main = main;
        this.date = date;
    }

    public boolean isTrueUser() {
        return trueUser;
    }

    public void setTrueUser(boolean trueUser) {
        this.trueUser = trueUser;
    }

    public TemplateDto getTemplate() {
        return template;
    }

    public void setTemplate(TemplateDto template) {
        this.template = template;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "trueUser=" + trueUser +
                ", template=" + template +
                ", main='" + main + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
