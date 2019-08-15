package activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import com.first.myapplication.R;

import bean.UserMessage;

/**
 * Created by 小黑 on 2019/8/15.
 */

public class UserMessageActivity extends Activity {
    private TextView account;
    private TextView username;
    private TextView province;
    private UserMessage userMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_item_usermessage);
        userMessage = (UserMessage) getIntent().getSerializableExtra("userdata");
        account = findViewById(R.id.tv_item_message_account);
        username = findViewById(R.id.tv_item_message_username);
        province = findViewById(R.id.tv_item_message_province);
        account.setText("账号:"+userMessage.getAccount());
        username.setText("用户名:"+userMessage.getUsername());
        province.setText("省份:"+userMessage.getProvince());
    }
    public void onClick(View v){
        finish();
    }
}
