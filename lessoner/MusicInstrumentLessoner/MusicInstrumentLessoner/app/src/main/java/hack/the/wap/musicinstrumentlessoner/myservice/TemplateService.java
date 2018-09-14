package hack.the.wap.musicinstrumentlessoner.myservice;

import java.util.HashMap;

import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateAssignmentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MusicTemplateDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class TemplateService {
    private static TemplateService instance;
    private static Session session = Session.getInstance();

    private void TemplateService() {

    }

    public static TemplateService getInstance() {
        if (instance == null) {
            instance = new TemplateService();
        }
        return instance;
    }

    public boolean isMyAssignment(MusicTemplateAssignmentDto assignmentDto) {
        if (assignmentDto.getStudentEmail().equals(session.getMainUser().getEmail())) {
            return true;
        }
        return false;
    }

    public MusicTemplateDto getMyAssignmentTemplate(MusicTemplateAssignmentDto assignmentDto) {
        for (MusicTemplateDto templateDto : session.getTemplates().values()) {
            if (templateDto.getMusicTemplateId() == assignmentDto.getMusicTemplateId()) {
                return templateDto;
            }
        }
        return null;
    }

    public HashMap<String, String> getTemplateLayoutInfo(MusicTemplateDto templateDto, MusicTemplateAssignmentDto assignmentDto) {
        HashMap<String, String> templateLayoutInfo = new HashMap<>();
        templateLayoutInfo.put("musician", templateDto.getMusician());
        templateLayoutInfo.put("musicTitle", templateDto.getMusicTitle());
        templateLayoutInfo.put("toDoCount", assignmentDto.getToDoCount() + "");
        templateLayoutInfo.put("successPercent", assignmentDto.getSuccessPercent() + "");
        templateLayoutInfo.put("teacher", templateDto.getOwner());
        return templateLayoutInfo;
    }

}
