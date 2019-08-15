package adapter.adapter.UserAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.first.myapplication.R;

/**
 * Created by 小黑 on 2019/8/15.
 */

public class UserListViewAdapter extends BaseAdapter {
    private Context context;

    public UserListViewAdapter(Context context){
            this.context = context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder;
        view = View.inflate(context, R.layout.item_user, null);
        holder = new ViewHolder(view,i);
        view.setTag(holder);

        return view;
    }
    class ViewHolder{
        TextView textView;
        public ViewHolder(View view,int i){
            textView = view.findViewById(R.id.user_item_tv);
            switch (i){
                case 0:
                    textView.setText("个人信息");
                    textView.setTextSize(30);
                    break;
                case 1:
                    textView.setText("个人信息修改");
                    textView.setTextSize(30);
                    break;
                case 2:
                    textView.setText("密码修改");
                    textView.setTextSize(30);
                    break;
                case 3:
                    textView.setText("图片库");
                    textView.setTextSize(30);
                    break;
            }
        }
    }
}
