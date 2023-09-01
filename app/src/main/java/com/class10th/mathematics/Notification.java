package com.class10th.mathematics;

import android.app.Application;

import com.onesignal.OneSignal;

public class Notification extends Application {
    private static final String ONESIGNAL_APP_ID = "3230a6df-fb4d-47bd-91bc-74c75bf7892a7";

    @Override
    public void onCreate() {
        super.onCreate();

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);

        OneSignal.promptForPushNotifications();

    }
}
