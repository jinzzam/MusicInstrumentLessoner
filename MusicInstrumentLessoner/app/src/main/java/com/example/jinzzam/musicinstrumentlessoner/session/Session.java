package com.example.jinzzam.musicinstrumentlessoner.session;

public class Session {
    private static Session instance;

    private Session() {

    }

    public static Session getInstance() {
        if (instance == null)
            instance = new Session();
        return instance;
    }
}
