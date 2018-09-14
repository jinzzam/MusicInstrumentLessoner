package hack.the.wap.musicinstrumentlessoner.myservice;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class UserInfoService {
    private static UserInfoService instance;
    private static Session session;

    private UserInfoService() {
        session = Session.getInstance();
    }

    public static UserInfoService getInstance() {
        if (instance == null) {
            instance = new UserInfoService();
        }
        return instance;
    }

    public String getUserName(String email) {
        for (MiUserDto userDto : session.getUsers().values()) {
            if (userDto.getEmail().equals(email)) {
                return userDto.getName();
            }
        }
        return null;
    }
}
