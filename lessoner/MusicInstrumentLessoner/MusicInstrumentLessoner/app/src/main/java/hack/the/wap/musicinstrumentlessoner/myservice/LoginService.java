package hack.the.wap.musicinstrumentlessoner.myservice;


import hack.the.wap.musicinstrumentlessoner.session.Session;

public class LoginService {

    private static Session session = Session.getInstance();
    private static LoginService instance;

    private LoginService() {

    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

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
