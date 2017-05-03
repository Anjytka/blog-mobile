package ru.ilyina.ann.blog;

import android.app.Application;

import ru.ilyina.ann.blog.service.ApiBuilder;

/**
 * Created by anjytka on 19.04.17.
 */

public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ApiBuilder.init(
                getResources().getString(R.string.host),
                getResources().getInteger(R.integer.port)
        );
    }
}
