package adapter.adapter.TypeAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.first.myapplication.R;

import java.util.List;

import bean.Commodity;

/**
 * Created by 小黑 on 2019/8/14.
 */

public class TypeListAdapter extends BaseAdapter {
    private List<Commodity> result;
    private Context mContext;
    public TypeListAdapter(Context mContext,List<Commodity> result){
        this.mContext = mContext;
        this.result = result;
    }
    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int i) {
        return result.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holer;
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_home_grid, null);
            holer = new ViewHolder(view);
            view.setTag(holer);
        } else {
            holer = (ViewHolder) view.getTag();
        }
        Commodity commodity = result.get(i);
        holer.tv_price.setText("价格" + commodity.getPrice() + "￥");
        holer.tv_commodityName.setText(commodity.getCommodityName());
        Glide.with(mContext)
                .load(commodity.getImage())
                .into(holer.iv_commodity);
        return view;
    }
    class ViewHolder{
        ImageView iv_commodity;
        TextView tv_price;
        TextView tv_commodityName;
        public ViewHolder(View view){
            iv_commodity = view.findViewById(R.id.iv_commodity);
            tv_price = view.findViewById(R.id.tv_price);
            tv_commodityName = view.findViewById(R.id.tv_commodityname);
        }
    }
}
