package com.example.jinzzam.musicinstrumentlessoner.model.dto;

import java.util.HashMap;

public class TeacherDto extends UserDto {

    private HashMap<String, TemplateDto> templates;

    {
        templates = new HashMap<>();
    }

    /**
     * create UserDto
     *
     * @param email    primary key
     * @param password not null
     * @param username not null
     */
    public TeacherDto(String email, String password, String username) {
        super(email, password, username);
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
