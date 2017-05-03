package ru.ilyina.ann.blog.service.command;


import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import ru.ilyina.ann.blog.dto.MArticle;
import ru.ilyina.ann.blog.service.ApiBuilder;

/**
 * Created by anjytka on 13.04.17.
 */

public class ArticleGetAllCommand extends BaseApiCommand<ArrayList<MArticle>> {

    private static final String TAG = "ArticleGetAllCommand";

    @Override
    public ArrayList<MArticle> call() throws Exception {
        Log.i(TAG, "call");
        Response<List<MArticle>> response = ApiBuilder.getInstance().articleApi.getData().execute();
        Log.i(TAG, "callAfterResponce");
        if (response.isSuccessful()) {
            return new ArrayList<>(response.body());
        } else {
            throw new RuntimeException(response.code() + ": " + response.message());
        }
    }
}



