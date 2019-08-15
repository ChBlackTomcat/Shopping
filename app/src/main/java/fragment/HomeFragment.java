package fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.first.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import adapter.adapter.HomeAdapter.HomeRecyclerViewAdapter;
import base.BaseFragment;
import bean.Commodity;
import okhttp3.Call;

/**
 * Created by 小黑 on 2019/8/6.
 */

public class HomeFragment extends BaseFragment {
    private RecyclerView rvHome;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private HomeRecyclerViewAdapter adapter;
    private List<Commodity> commodity;

    @Override
    public View initView() {
        View view = View.inflate(myContext, R.layout.fragment_home,null);
        tv_search_home = (TextView)view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView)view.findViewById(R.id.tv_message_home);
        rvHome = (RecyclerView)view.findViewById(R.id.rv_home);
        return view;
    }
    public void initData(){
        getDataFromHttp();
    }


    public void getDataFromHttp() {
        String url = myContext.getResources().getString(R.string.httpUrl);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("choose","getHomeResultBean")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if(response!=null){
                            Log.e("TAG",response);
                            processData(response);
                            adapter = new HomeRecyclerViewAdapter(myContext,commodity);
                            rvHome.setAdapter(adapter);
                            GridLayoutManager manager = new GridLayoutManager(getActivity(), 1);
                            rvHome.setLayoutManager(manager);
                        }
                    }
                });
    }

    public void processData(String json){
        if (!TextUtils.isEmpty(json)){
            commodity = new Gson().fromJson(json,new TypeToken<List<Commodity>>(){}.getType());
        }
    }

}
