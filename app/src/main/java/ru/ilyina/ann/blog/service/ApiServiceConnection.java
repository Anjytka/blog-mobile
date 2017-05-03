package ru.ilyina.ann.blog.service;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.Serializable;

import ru.ilyina.ann.blog.service.ApiService.ApiBinder;
import ru.ilyina.ann.blog.service.command.ApiCommand;

/**
 * Created by anjytka on 29.03.17.
 */

public class ApiServiceConnection implements ServiceConnection {
    private static final String TAG = "ApiServiceConnection";

    private static ApiService apiService = null;

    @Override
    public void onServiceConnected(ComponentName name, IBinder binder) {
        this.apiService = ((ApiBinder)binder).getService();
        Log.d(TAG, "Service connected");

    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.d(TAG, "Service disconnected");
        this.apiService = null;
    }

    public <T extends Serializable> void execute(ApiCommand<T> command, Handler handler)  {
        apiService.execute(command, handler);
    }
}
