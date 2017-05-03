package ru.ilyina.ann.blog.service;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.ilyina.ann.blog.service.api.ArticleApi;

/**
 * Created by anjytka on 19.04.17.
 */

public class ApiBuilder {

    private static final String TAG = "ApiBuilder";

    private static volatile ApiBuilder apiBuilder;
    private Retrofit retrofit;

    public static ArticleApi articleApi;

    private ApiBuilder(String host, Integer port) {
        retrofit = new Retrofit.Builder()
                .baseUrl(host + ":" + port)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        articleApi = retrofit.create(ArticleApi.class);
    }

    public static ApiBuilder init(String host, Integer port) {
        try {
            Log.i(TAG, "init");
            if (apiBuilder == null) {
                synchronized (ApiBuilder.class) {
                    if (apiBuilder == null) {
                        apiBuilder = new ApiBuilder(host, port);
                    }
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "Error occurd during init ApiBuilder");
        }
        return apiBuilder;
    }

    public static ApiBuilder getInstance() {
        Log.i(TAG, "getInstance");
        if (apiBuilder == null) {
            throw new IllegalStateException("Class was not initialized. Please call init first.");
        }
        return apiBuilder;
    }
}
