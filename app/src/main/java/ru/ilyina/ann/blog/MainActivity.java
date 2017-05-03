package ru.ilyina.ann.blog;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import ru.ilyina.ann.blog.adapter.ArticleAdapter;
import ru.ilyina.ann.blog.service.ApiService;
import ru.ilyina.ann.blog.service.ApiServiceConnection;
import ru.ilyina.ann.blog.service.handler.ServiceHandler;
import ru.ilyina.ann.blog.service.command.ArticleGetAllCommand;
import ru.ilyina.ann.blog.dto.MArticle;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";

    private ApiServiceConnection mServiceConnection = new ApiServiceConnection();
    private ArticleAdapter dataAdapter = new ArticleAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);;

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        ArticleListFragment articleListFragment = new ArticleListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putBinder(ArticleListFragment.SERVICE_CONNECTION_STR, mServiceConnection.getBinder());
//        articleListFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.activity_main, articleListFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
