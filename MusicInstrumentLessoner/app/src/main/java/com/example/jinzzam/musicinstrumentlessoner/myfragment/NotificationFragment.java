package com.example.jinzzam.musicinstrumentlessoner.myfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

public class NotificationFragment extends Fragment{
    private static View notificationFragmentView;

    private static NotificationFragment instance;

    public static NotificationFragment getInstance() {
        if (instance == null)
            instance = new NotificationFragment();
        return instance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

}
