package com.example.jinzzam.musicinstrumentlessoner.myfragment;

public class TemplateFragment {

    private static TemplateFragment instance;

    public static TemplateFragment getInstance() {
        if (instance == null)
            instance = new TemplateFragment();
        return instance;
    }
}
