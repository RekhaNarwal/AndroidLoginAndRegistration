package com.socialpresencenetwork.phodo;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by Mark on 4/20/2015.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "fm5RVzedrYaLNMHIlKz9dvsKoMu7H0jTQRi24EE4", "ssWl0YqtA4Q92uQFrVp0VNVNcwibH4j3VslcHaKy");
    }
}
