package hack.the.wap.musicinstrumentlessoner.myservice;

import hack.the.wap.musicinstrumentlessoner.model.dto.MiNotificationDto;
import hack.the.wap.musicinstrumentlessoner.session.Session;

public class NotificationService {
    private static Session session = Session.getInstance();
    private static NotificationService instance;

    private NotificationService() {

    }

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public boolean isMine(MiNotificationDto dto) {
        if (dto.getEmail().equals(session.getMainUser().getEmail())) {
            return true;
        }
        return false;
    }
}
