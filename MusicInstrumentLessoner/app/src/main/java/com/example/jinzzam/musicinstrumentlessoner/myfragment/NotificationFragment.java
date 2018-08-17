package com.example.jinzzam.musicinstrumentlessoner.myfragment;

public class NotificationFragment {

    private static NotificationFragment instance;

    public static NotificationFragment getInstance() {
        if (instance == null)
            instance = new NotificationFragment();
        return instance;
    }
}
