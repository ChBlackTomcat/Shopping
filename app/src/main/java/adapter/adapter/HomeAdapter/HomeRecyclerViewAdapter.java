package adapter.adapter.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.first.myapplication.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import activity.GoodsActivity;
import bean.Commodity;



/**
 * Created by 小黑 on 2019/8/6.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Commodity> commodity;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;
    private final int BANNER = 0;
    private int currentType = BANNER;
    private final int COMMODITY = 1;
    public HomeRecyclerViewAdapter(Context mContext,List<Commodity> commodity){
        this.commodity = commodity;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BANNER) {
            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.fragment_home_banner, null), mContext);
        } else if (viewType == COMMODITY) {
            return new CommodityViweHolder(mLayoutInflater.inflate(R.layout.fragment_home_commodity, null), mContext);
        }
        return null;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(getItemViewType(position)==BANNER){
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.setData();
        }else if(getItemViewType(position)==COMMODITY) {
            CommodityViweHolder commodityViweHolder = (CommodityViweHolder) holder;
            commodityViweHolder.setData(commodity, position);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
            if(position==0){
                currentType = BANNER;
            }else {
                currentType = COMMODITY;
            }
        return currentType;
    }
    class CommodityViweHolder extends  RecyclerView.ViewHolder{
        private ListView listView;
        private Context mContext;
        public CommodityViweHolder(View itemView,Context mContext) {
            super(itemView);
            listView = itemView.findViewById(R.id.home_listView);
            this.mContext = mContext;
        }
        public void setData(final List<Commodity> result,int position){
            listView.setAdapter(new CommodityAdapter(mContext,result));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(mContext, GoodsActivity.class);
                    intent.putExtra("data",result.get(i));
                    mContext.startActivity(intent);
                }
            });
        }

    }
    class BannerViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private Banner banner;
        public BannerViewHolder(View itemView,Context mContext) {
            super(itemView);
            this.mContext = mContext;
            banner = itemView.findViewById(R.id.banner);
        }
        public void setData(){
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            String image1 = mContext.getResources().getString(R.string.banner1);
            String image2 = mContext.getResources().getString(R.string.banner2);
            String image3 = mContext.getResources().getString(R.string.banner3);
            List<String> imageUri = new ArrayList<String>();
            imageUri.add(image1);
            imageUri.add(image2);
            imageUri.add(image3);
            banner.setBannerAnimation(Transformer.Accordion);
            banner.setImageLoader(new BannerImageLoad());
            banner.setImages(imageUri);
            banner.start();

        }
    }
    class BannerImageLoad extends ImageLoader{
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
