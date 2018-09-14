package hack.the.wap.musicinstrumentlessoner.myservice;


import java.util.ArrayList;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiGroupDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiStudentDto;
import hack.the.wap.musicinstrumentlessoner.model.dto.MiTeacherDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class GroupService {
    private static GroupService instance;
    private static Session session = Session.getInstance();

    private GroupService() {

    }

    public static GroupService getInstance() {
        if (instance == null) {
            instance = new GroupService();
        }
        return instance;
    }

    public boolean isMyGroup(MiGroupDto dto) {
        for (MiTeacherDto teacherDto : session.getGroupTeachers().values()) {
            if (dto.getGroupName().equals(teacherDto.getGroupName()) && teacherDto.getTeacherEmail().equals(session.getMainUser().getEmail())) {
                return true;
            }
        }
        for (MiStudentDto studentDto : session.getGroupStudents().values()) {
            if (dto.getGroupName().equals(studentDto.getGroupName()) && studentDto.getStudentEmail().equals(session.getMainUser().getEmail())) {
                return true;
            }
        }
        return false;
    }
}
