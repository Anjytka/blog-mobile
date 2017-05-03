package ru.ilyina.ann.blog.service.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.ilyina.ann.blog.dto.MArticle;

/**
 * Created by anjytka on 13.04.17.
 */

public interface ArticleApi {
    @GET("/api/articles")
    Call<List<MArticle>> getData();
}
