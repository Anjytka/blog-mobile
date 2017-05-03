package ru.ilyina.ann.blog;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.ilyina.ann.blog.adapter.ArticleAdapter;
import ru.ilyina.ann.blog.dto.MArticle;
import ru.ilyina.ann.blog.service.ApiService;
import ru.ilyina.ann.blog.service.ApiServiceConnection;
import ru.ilyina.ann.blog.service.command.ArticleGetAllCommand;
import ru.ilyina.ann.blog.service.handler.ServiceHandler;

/**
 * Created by anjytka on 20.04.17.
 */

public class ArticleListFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "ArticleListFragment";

    public static final String SERVICE_CONNECTION_STR = "SERVICE_CONNECTION_STR";

    private ArticleAdapter dataAdapter = new ArticleAdapter();

    private ApiServiceConnection mServiceConnection = new ApiServiceConnection();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        super.onCreate(savedInstanceState);
        view.findViewById(R.id.get_data).setOnClickListener(this);

        getActivity().bindService(new Intent(getActivity().getBaseContext(), ApiService.class), mServiceConnection, Context.BIND_AUTO_CREATE);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataAdapter);
    }

    @Override
    public void onClick(View view) {
        Log.i(TAG, "onClick");
        switch (view.getId()) {
            case R.id.get_data:
                getData();
                break;
        }
    }

    private void getData() {
        Log.i(TAG, "getData");
        ServiceHandler<ArticleListFragment> handler = new ArticleListFragment.ServiceHandlerImpl(this);
        mServiceConnection.execute(new ArticleGetAllCommand(), handler);
    }

    private static class ServiceHandlerImpl extends ServiceHandler<ArticleListFragment> {
        public ServiceHandlerImpl(ArticleListFragment fragment) {
            super(fragment);
        }

        @Override
        public void handleMessage(ArticleListFragment fragment, Message msg) {
            @SuppressWarnings("unchecked")
            ArrayList<MArticle> result = (ArrayList<MArticle>)msg.getData().getSerializable("result");
            fragment.dataAdapter.setData(result);
            fragment.dataAdapter.notifyDataSetChanged();
        }
    }
}
