package adapter.adapter.ShopCartAdapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.first.myapplication.R;

import java.util.List;

import adapter.adapter.TypeAdapter.TypeListAdapter;
import bean.ShopCartData;

/**
 * Created by 小黑 on 2019/8/15.
 */

public class ShopCartListViewAdapter extends BaseAdapter {
    private List<ShopCartData> datas;
    private Context context;

    public ShopCartListViewAdapter(Context context,List<ShopCartData> datas){
        this.context = context;
        this.datas = datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(context, R.layout.shopcart_items, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ShopCartListViewAdapter.ViewHolder) view.getTag();
        }
        ShopCartData data = datas.get(i);
        Log.e("TAG",data.getCommodityName()+data.getPrice()+data.getNumber());
        holder.commodityName.setText(data.getCommodityName());
        holder.prvice.setText("单价:"+data.getPrice());
        holder.number.setText("数量:"+data.getNumber());
        Glide.with(context)
                .load(data.getImage())
                .into(holder.imageView);
        return view;
    }
    class ViewHolder{
        private ImageView imageView;
        private TextView commodityName;
        private TextView prvice;
        private TextView number;
        public ViewHolder(View v){
            imageView = v.findViewById(R.id.shopcart_image);
            commodityName = v.findViewById(R.id.shopcart_commodityName);
            prvice = v.findViewById(R.id.shopcart_prvice);
            number = v.findViewById(R.id.shopcart_number);
        }
    }
}
