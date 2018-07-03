package pe.cajami.com.cursoandroid.configuration;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class MehanicalApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
