package ru.ilyina.ann.blog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.ilyina.ann.blog.R;
import ru.ilyina.ann.blog.dto.MArticle;

/**
 * Created by anjytka on 13.04.17.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<MArticle> articlesList = new ArrayList<>();

    private final static int BLOG = 1;
    private final static int ADVERTISE = 2;
    private final static String ADVERTISE_NAME = "star";

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public TextView name, context;
        public ImageView imageView;

        public ArticleViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.title);
            context = (TextView) view.findViewById(R.id.content);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }

    public class AdvertiseViewHolder extends ArticleViewHolder {
        public AdvertiseViewHolder(View view) {
            super(view);
        }
    }

    public void setData(List<MArticle> articlesList) {
        this.articlesList = articlesList;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ADVERTISE) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.advertise_item, parent, false);
            return new AdvertiseViewHolder(itemView);
        }
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item, parent, false);

        return new ArticleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        MArticle article = articlesList.get(position);
        holder.name.setText(article.getTitle());
        holder.context.setText(article.getContent());
        Context appContext = holder.view.getContext();
        Picasso.with(appContext).load(
                appContext.getResources().getString(R.string.host)
                        + ':'
                        + appContext.getResources().getInteger(R.integer.port)
                        + appContext.getResources().getString(R.string.resources)
                        + article.getImageName()
                        + ".png")
                .into(holder.imageView);
    }

    @Override
    public int getItemViewType(int position) {
        if (articlesList.get(position).getImageName().equals(ADVERTISE_NAME)) {
            return ADVERTISE;
        } else {
            return BLOG;
        }
    }

    @Override
    public int getItemCount() {
        return articlesList.size();
    }
}
