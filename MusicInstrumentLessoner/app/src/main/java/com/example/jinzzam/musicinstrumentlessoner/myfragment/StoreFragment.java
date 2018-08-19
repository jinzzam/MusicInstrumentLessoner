package com.example.jinzzam.musicinstrumentlessoner.myfragment;

import android.support.v4.app.Fragment;

public class StoreFragment extends Fragment{
    private static StoreFragment instance;

    public static StoreFragment getInstance() {
        if (instance == null)
            instance = new StoreFragment();
        return instance;
    }
}
