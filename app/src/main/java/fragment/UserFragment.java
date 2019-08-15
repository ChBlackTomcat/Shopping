package fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.first.myapplication.R;

import activity.ImageBaseActivity;
import activity.RevisionMessageActivity;
import activity.RevisionPasswordActivity;
import activity.UserMessageActivity;
import adapter.adapter.UserAdapter.UserListViewAdapter;
import base.BaseFragment;
import bean.Commodity;
import bean.UserMessage;
import utilse.Utils;

/**
 * Created by 小黑 on 2019/8/9.
 */

public class UserFragment extends BaseFragment {
    private ListView listView;
    private UserListViewAdapter adapter;
    private Intent intent;
    private Cursor cursor;
    private UserMessage userMessage;
    private String username;
    private String account ;
    private String password;
    private String province ;
    @Override
    public View initView() {
        View view = View.inflate(myContext, R.layout.fragment_user,null);
        listView = view.findViewById(R.id.user_listView);
        return view;
    }

    @Override
    public void initData() {
        cursor = Utils.serach(myContext);
        cursor.moveToFirst();
        username = cursor.getString(1);
        account = cursor.getString(2);
        password = cursor.getString(3);
        province = cursor.getString(4);
        userMessage = new UserMessage();
        userMessage.setUsername(username);
        userMessage.setAccount(account);
        userMessage.setPassword(password);
        userMessage.setProvince(province);
        adapter = new UserListViewAdapter(myContext);
        listView.setAdapter(adapter);
        initListener();
    }

    private void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        intent = new Intent(myContext, UserMessageActivity.class);
                        intent.putExtra("userdata",userMessage);
                        myContext.startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(myContext, RevisionMessageActivity.class);
                        intent.putExtra("user",userMessage);
                        myContext.startActivity(intent);
                        break;
                    case 2:
                        view = LayoutInflater.from(myContext).inflate(R.layout.poupwindows_revisionpassword, null, false);
                        Button yes = view.findViewById(R.id.poupwindows_password_yes);
                       final EditText editText = view.findViewById(R.id.et_poupwindows_password);
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
                        popWindow.showAsDropDown(listView, 50, 400);
                        yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(editText.getText().toString().equals(password)){
                                    intent = new Intent(myContext, RevisionPasswordActivity.class);
                                    intent.putExtra("databean",userMessage);
                                    myContext.startActivity(intent);
                                }else{
                                    Toast.makeText(myContext,"密码错误，无法进行修改",Toast.LENGTH_SHORT).show();
                                }
                                popWindow.dismiss();
                            }
                        });

                        break;
                    case 3:
                        intent = new Intent(myContext, ImageBaseActivity.class);
                        myContext.startActivity(intent);
                        break;
                }
            }
        });
    }
}
