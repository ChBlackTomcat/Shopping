package fragment;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.first.myapplication.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import activity.GoodsActivity;
import adapter.adapter.TypeAdapter.TypeListAdapter;
import base.BaseFragment;
import bean.Commodity;
import okhttp3.Call;
import utilse.Utils;


/**
 * Created by 小黑 on 2019/8/9.
 */

public class TypeFragment extends BaseFragment {
    private List<Commodity> resultBean;
    private RadioGroup rg;
    private ListView listView;
    private TypeListAdapter adapter;
    private int position = 0;
    private final int ALL = 0;
    private final int CLOTHES = 1;
    private final int BOOK = 2;
    private final int FOOD = 3;
    private final int HOT = 4;
    @Override
    public View initView() {
        View view = View.inflate(myContext, R.layout.fragment_type,null);
        rg = view.findViewById(R.id.type_rg);
        listView = view.findViewById(R.id.type_listView);
        return view;
    }

    @Override
    public void initData() {
        getDataFromHttp();

    }

    private void initListenter() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.type_rb_all:
                        position = ALL;
                        break;
                    case R.id.type_rb_clothes:
                        position = CLOTHES;
                        break;
                    case R.id.type_rb_book:
                        position = BOOK;
                        break;
                    case R.id.type_rb_food:
                        position = FOOD;
                        break;
                    case R.id.type_rb_hot:
                        position = HOT;
                        break;
                }
                switchType(position);
            }
        });
        rg.check(R.id.type_rb_all);
    }
    public void switchType(int position){
        switch (position){
            case ALL:
                adapter = new TypeListAdapter(myContext,resultBean);
                listView.setAdapter(adapter);
                break;
            case CLOTHES:
                adapter = new TypeListAdapter(myContext, Utils.chooseShow("衣服",resultBean));
                listView.setAdapter(adapter);
                break;
            case BOOK:
                adapter = new TypeListAdapter(myContext, Utils.chooseShow("书籍",resultBean));
                listView.setAdapter(adapter);
                break;
            case FOOD:
                adapter = new TypeListAdapter(myContext, Utils.chooseShow("食品",resultBean));
                listView.setAdapter(adapter);
                break;
            case HOT:
                adapter = new TypeListAdapter(myContext, Utils.chooseShow("热卖",resultBean));
                listView.setAdapter(adapter);
                break;

        }
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
                           if(response!=null) {
                               processData(response);
                               Log.e("TAG", "23333333333333");
                               initListenter();
                               listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                                   @Override
                                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                       Intent intent = new Intent(myContext, GoodsActivity.class);
                                       intent.putExtra("data",resultBean.get(i));
                                       myContext.startActivity(intent);
                                   }
                               });
                           }
                    }
                });
    }
    public void processData(String json){
        if (!TextUtils.isEmpty(json)){
            resultBean = new Gson().fromJson(json,new TypeToken<List<Commodity>>(){}.getType());
        }
    }

}
