package com.example.jinzzam.musicinstrumentlessoner.myfragment;

import android.support.v4.app.Fragment;

public class TemplateFragment extends Fragment {

    private static TemplateFragment instance;

    public static TemplateFragment getInstance() {
        if (instance == null)
            instance = new TemplateFragment();
        return instance;
    }
}
