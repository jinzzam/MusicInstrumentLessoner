package com.example.jinzzam.musicinstrumentlessoner.myfragment;

import android.support.v4.app.Fragment;

public class GroupFragment extends Fragment {

    private static GroupFragment instance;

    public static GroupFragment getInstance() {
        if (instance == null)
            instance = new GroupFragment();
        return instance;
    }
}
