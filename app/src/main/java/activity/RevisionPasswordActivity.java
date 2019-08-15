package activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.first.myapplication.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import bean.UserMessage;
import okhttp3.Call;

/**
 * Created by 小黑 on 2019/8/15.
 */

public class RevisionPasswordActivity extends Activity{
    private Button yes;
    private EditText word;
    private EditText reword;
    private UserMessage userMessage;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_item_revisionpassword);
        userMessage = (UserMessage) getIntent().getSerializableExtra("databean");
        yes = findViewById(R.id.revisionpassword_yes);
        word = findViewById(R.id.revisionpassword_ed1);
        reword = findViewById(R.id.revisionpassword_ed2);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = word.getText().toString();
                String repassword = word.getText().toString();
                String url = getResources().getString(R.string.httpUrl);
                if(password.equals(repassword)){
                    OkHttpUtils
                            .post()
                            .url(url)
                            .addParams("account",userMessage.getAccount())
                            .addParams("repassword",password)
                            .addParams("password", userMessage.getPassword())
                            .addParams("choose","revisionPassword")
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {

                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    if(response!=null){
                                        Toast.makeText(RevisionPasswordActivity.this,"密码修改成功",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }
                            });
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(RevisionPasswordActivity.this);
                    builder.setTitle("提示");
                    builder.setMessage("两次输入不一致");
                    builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
    }
    public void onClick(View v){
        finish();
    }
}
