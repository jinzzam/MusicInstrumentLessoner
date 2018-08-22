package hack.the.wap.musicinstrumentlessoner.model.dto;

import java.util.HashMap;

public class TeacherDto extends UserDto {
    private HashMap<String, TemplateDto> templates;

    {
        templates = new HashMap<>();
    }

    /**
     * Create TeacherDto
     *
     * @param name
     * @param email
     * @param password
     */
    public TeacherDto(String name, String email, String password) {
        super(name, email, password);
    }

    public HashMap<String, TemplateDto> getTemplates() {
        return templates;
    }

    public void setTemplates(HashMap<String, TemplateDto> templates) {
        this.templates = templates;
    }

    public void addTemplate(TemplateDto dto) {
        templates.put(dto.getMusicTitle(), dto);
    }
}
