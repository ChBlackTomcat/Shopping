package activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.first.myapplication.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import bean.UserMessage;
import okhttp3.Call;

/**
 * Created by 小黑 on 2019/8/15.
 */

public class RevisionMessageActivity extends Activity {
    private UserMessage userMessage;
    private EditText register_account;
    private EditText register_ed_username;
    private Button register_bt_confirm;
    private RadioGroup rg;
    private String province;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_item_revisionmessage);
        userMessage = (UserMessage) getIntent().getSerializableExtra("user");
        register_ed_username = findViewById(R.id.register_ed_username);
        register_account = findViewById(R.id.register_account);
        register_bt_confirm = findViewById(R.id.register_bt_confirm);
        rg = findViewById(R.id.register_rd_province);
        register_account.setText(userMessage.getAccount());
        register_ed_username.setText(userMessage.getUsername());
        register_bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = register_account.getText().toString();
                String username = register_ed_username.getText().toString();
                province = radiButtonOf(rg.getCheckedRadioButtonId());
                initHttp(account,username,province);
            }
        });

    }
    public void onClick(View v){
        finish();
    }
    private String radiButtonOf(int id){
        String province = null;
        switch (id){
            case R.id.rd_guangdon:
                province="广东";
                break;
            case R.id.rd_guangXi:
                province="广西";
                break;
            case R.id.rd_heNan:
                province="河南";
                break;
            case R.id.rd_huBei:
                province="湖北";
                break;
        }
        return province;

    }
    private void initHttp(String account,String username,String province) {
        String url = getResources().getString(R.string.httpUrl);
        OkHttpUtils
                .post()
                .url(url)
                .addParams("account",userMessage.getAccount())
                .addParams("password",userMessage.getPassword())
                .addParams("reaccount", account)
                .addParams("reusername",username)
                .addParams("reprovince",province)
                .addParams("choose","revisionUserMessage")
                .build()
                .execute(new StringCallback(){

                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                            if(response!=null){
                                Toast.makeText(RevisionMessageActivity.this,"信息修改成功",Toast.LENGTH_LONG).show();
                                finish();
                            }
                    }

                });
    }

}
