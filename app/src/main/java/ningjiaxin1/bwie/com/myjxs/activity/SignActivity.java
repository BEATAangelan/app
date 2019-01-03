package ningjiaxin1.bwie.com.myjxs.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.api.Api;
import ningjiaxin1.bwie.com.myjxs.bean.Sign;
import ningjiaxin1.bwie.com.myjxs.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.myjxs.view.IView;

public class SignActivity extends AppCompatActivity implements IView {
    @BindView(R.id.but_login)
    TextView button;
    @BindView(R.id.phone_num)
    EditText phone_num;
    @BindView(R.id.phone_pwd)
    EditText phone_pwd;
    @BindView(R.id.but_regin)
    Button button_regin;
    IPersentermpl iPersentermpl;
    String path="http://172.17.8.100/small/user/v1/register";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        iPersentermpl = new IPersentermpl(this);
    }
    @OnClick(R.id.but_regin)
    public void regin(){
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone_num.getText().toString());
        map.put("pwd",phone_pwd.getText().toString());
        iPersentermpl.startpostRequest(path,map,Sign.class);
        Intent intent = new Intent(SignActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.but_login)
    public void login(){
        Intent intent = new Intent(SignActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setonSuccess(Object o) {
       Sign bean= (Sign) o;
       if(bean.getStatus().equals("0000")){
           Intent intent = new Intent(SignActivity.this, MainActivity.class);
           startActivity(intent);
           Toast.makeText(SignActivity.this,"成功！",Toast.LENGTH_SHORT).show();
       }
       else {
           Toast.makeText(SignActivity.this,"失败了呦！",Toast.LENGTH_SHORT).show();
       }
    }

    @Override
    public void onfailed(Exception e) {

    }
}
