package com.shonen.ukr.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App  extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("W9xqQaYhx8EiDUCP7SjkXFcnxoLTQN2RtdZY0i0c")
                // if defined
                .clientKey("50QAGTRO4zuQjkYvQCxSBfwtGX8wwj0XbK6Xvb6R")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}


