package fragment;

import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.first.myapplication.R;

import java.util.ArrayList;
import java.util.List;

import adapter.adapter.ShopCartAdapter.ShopCartListViewAdapter;
import base.BaseFragment;
import bean.ShopCartData;
import utilse.Utils;

/**
 * Created by 小黑 on 2019/8/9.
 */

public class ShopCartFragment extends BaseFragment {
    private ListView listView;
    private Cursor cursor;
    private List<ShopCartData> datas;
    private ShopCartListViewAdapter adapter;
    @Override
    public View initView() {
        cursor = Utils.serachShopCartData(myContext);
        Log.e("TAG",cursor.getCount()+"");
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            View  view = View.inflate(myContext, R.layout.fragment_shopcart, null);
            listView = view.findViewById(R.id.shopcart_listview);
            return view;
        }else{
          TextView textView = new TextView(myContext);
            textView.setText("你还没有添加任何商品");
            textView.setTextSize(30);
            return textView;
        }

    }

    @Override
    public void initData() {
        if(cursor.getCount()>0) {
            ShopCartData result;
            datas = new ArrayList<>();
            result = new ShopCartData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            datas.add(result);
            while (cursor.moveToNext()) {
                result = new ShopCartData(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
                datas.add(result);
            }
            adapter = new ShopCartListViewAdapter(myContext, datas);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                }
            });
        }
    }
}
