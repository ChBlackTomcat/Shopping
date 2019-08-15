package activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.first.myapplication.R;

import bean.Commodity;
import utilse.Utils;

/**
 * Created by 小黑 on 2019/8/13.
 */

public class GoodsActivity extends Activity implements View.OnClickListener {
    private Commodity commodity;
    private ImageView iv_goods;
    private TextView tv_goods_commodityName;
    private TextView tv_goods_price;
    private TextView tv_goods_intro;
    private Button bt_goods_add;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        commodity = (Commodity) getIntent().getSerializableExtra("data");
        iv_goods = findViewById(R.id.iv_goods);
        tv_goods_commodityName = findViewById(R.id.tv_goods_commodityName);
        tv_goods_price = findViewById(R.id.tv_goods_price);
        tv_goods_intro = findViewById(R.id.tv_goods_intro);
        bt_goods_add = findViewById(R.id.bt_goods_add);
        Glide.with(this)
                .load(commodity.getImage())
                .into(iv_goods);
        tv_goods_commodityName.setText(commodity.getCommodityName());
        tv_goods_price.setText("价格："+commodity.getPrice()+"￥");
        tv_goods_intro.setText(commodity.getIntro());
        bt_goods_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        initPoupWiodows(view);
    }

    private void initPoupWiodows(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.goods_popuwindows, null, false);
        Button reduce = view.findViewById(R.id.goods_poupwindows_reduce);
        Button add = view.findViewById(R.id.goods_poupwindows_add);
        Button yes = view.findViewById(R.id.bt_goods_poupwindows_yes);
        final EditText number = view.findViewById(R.id.goods_ed);
        final PopupWindow popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(-1);
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;

            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popWindow.showAsDropDown(v, 50, 400);
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsActivity.this, "你点击了减少~", Toast.LENGTH_SHORT).show();
                int i = Integer.parseInt(number .getText().toString()) ;
                i--;
                number.setText(i+"");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GoodsActivity.this, "你点击了添加~", Toast.LENGTH_SHORT).show();
                int i = Integer.parseInt(number .getText().toString()) ;
                i++;
                number.setText(i+"");
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(number .getText().toString()) ;
                Utils.insertForShopCart(GoodsActivity.this,commodity,count);
                popWindow.dismiss();
            }
        });


    }
    public void backOnemore(View v){
        finish();
    }
}
