package ru.ilyina.ann.blog.service.handler;

import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by anjytka on 23.03.17.
 */

public abstract class ServiceHandler<T> extends Handler {

    private WeakReference<T> element;

    public ServiceHandler(T fragment) {
        this.element = new WeakReference<T>(fragment);
    }

    @Override
    public void handleMessage(Message msg) {
        T element = this.element.get();
        if (element != null && msg != null) {
            handleMessage(element, msg);
        }
    }

    public abstract void handleMessage(T element, Message msg);
}