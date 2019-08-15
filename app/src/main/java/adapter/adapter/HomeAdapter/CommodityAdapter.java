package adapter.adapter.HomeAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
 * Created by 小黑 on 2019/8/13.
 */

public class CommodityAdapter extends BaseAdapter {
    private List<Commodity> resultBean;
    private Context mContext;

    public CommodityAdapter(Context mContext,List<Commodity> resultBean){
        this.mContext = mContext;
        this.resultBean = resultBean;
    }
    @Override
    public int getCount() {

        return resultBean.size();
    }

    @Override
    public Object getItem(int i) {
        return resultBean.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Log.e("TAG",resultBean.size()+"");
        Log.e("TAG",viewGroup.getChildCount()+"");
        ViewHolder holer;
        if (view == null) {
            view = View.inflate(mContext, R.layout.fragment_home_grid, null);
            holer = new ViewHolder(view);
            view.setTag(holer);
        } else {
            holer = (ViewHolder) view.getTag();
        }
        Commodity commodity = resultBean.get(i);
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
