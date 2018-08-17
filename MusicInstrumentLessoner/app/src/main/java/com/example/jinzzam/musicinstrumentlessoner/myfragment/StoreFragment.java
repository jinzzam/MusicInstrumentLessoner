package com.example.jinzzam.musicinstrumentlessoner.myfragment;

public class StoreFragment {
    private static StoreFragment instance;

    public static StoreFragment getInstance() {
        if (instance == null)
            instance = new StoreFragment();
        return instance;
    }
}
