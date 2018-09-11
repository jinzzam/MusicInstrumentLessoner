package hack.the.wap.musicinstrumentlessoner.myservice;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiUserDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginService {

    private static Session session = Session.getInstance();

    public boolean checkEmail(String inputEmail) {
        if (session.getMainUser().getEmail().equals(inputEmail)) {
            return true;
        }
        return false;
    }

    public boolean checkPassword(String inputPassword) {
        if (session.getMainUser().getPassword().equals(inputPassword)) {
            return true;
        }
        return false;
    }
}
