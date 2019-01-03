package ningjiaxin1.bwie.com.myjxs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ningjiaxin1.bwie.com.myjxs.R;
import ningjiaxin1.bwie.com.myjxs.api.Api;
import ningjiaxin1.bwie.com.myjxs.bean.Login;
import ningjiaxin1.bwie.com.myjxs.persenter.IPersentermpl;
import ningjiaxin1.bwie.com.myjxs.utils.BaseActionBar;
import ningjiaxin1.bwie.com.myjxs.utils.NetWorkUtils;
import ningjiaxin1.bwie.com.myjxs.utils.RegularUtils;
import ningjiaxin1.bwie.com.myjxs.view.IView;

public class MainActivity extends BaseActionBar implements IView {
    @BindView(R.id.edit_phone_num)
    EditText phone_num;
    @BindView(R.id.edit_phone_pwd)
    EditText phone_pwd;
    @BindView(R.id.login_button)
    Button button;
    @BindView(R.id.checkbox_remeber)
    CheckBox checkBox;
    @BindView(R.id.text_sign)
    TextView textView;
    @BindView(R.id.image_eye)
    ImageView image;
    private IPersentermpl iPersentermpl;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String path="http://172.17.8.100/small/user/v1/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.login_button);
        ButterKnife.bind(this);
        init();
    }
    private void init(){
        iPersentermpl=new IPersentermpl(this);
        sharedPreferences= getSharedPreferences("User", MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //进行判断是否记住密码
        boolean r_ischeck = sharedPreferences.getBoolean("r_ischeck", false);

        if(r_ischeck){
            String num = sharedPreferences.getString("num", null);
            String pwd = sharedPreferences.getString("pwd", null);
            phone_num.setText(num);
            phone_pwd.setText(pwd

            );
            checkBox.setChecked(true);
        }
        //点击记住密码
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    editor.putString("num",phone_num.getText().toString());
                    editor.putString("pwd",phone_pwd.getText().toString());
                    editor.putBoolean("r_ischeck",true);
                    editor.commit();
                }else
                {
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }
    @OnClick(R.id.text_sign)
    public void setbutton(){
        Intent intent = new Intent(MainActivity.this, SignActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.login_button)
    public void login(){
        String phone_num1 = phone_num.getText().toString();
        String phone_pwd1 = phone_pwd.getText().toString();
        //判断手机号
          if(RegularUtils.isPhoneValidator(phone_num1)){
              //判断密码
              if (RegularUtils.isPwdValidator(phone_pwd1)){

                  Map<String,String> map=new HashMap<>();
                  map.put("phone",phone_num1);
                  map.put("pwd",phone_pwd1);
                  iPersentermpl.startpostRequest(path,map,Login.class);
              }else {
                  Toast.makeText(this,"小可爱，密码是6-16位的数字哦",Toast.LENGTH_SHORT).show();
              }
          }
          else {
              Toast.makeText(this,"小可爱，手机是11位的数字哦",Toast.LENGTH_SHORT).show();

          }

    }

    @Override
    public void setonSuccess(Object o) {
         Login bean= (Login) o;
         if(bean.getStatus().equals("0000")){
             if(NetWorkUtils.isGG(this)){
             Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                 startActivity(intent);
             }
             else {
                 Toast.makeText(MainActivity.this,"你竟然不开网！！！",Toast.LENGTH_SHORT).show();
             }
         }
         else
         {
             Toast.makeText(MainActivity.this,"你是不是忘记密码了！￣へ￣",Toast.LENGTH_SHORT).show();
         }
    }

    @Override
    public void onfailed(Exception e) {
        Toast.makeText(MainActivity.this,"失败！！！",Toast.LENGTH_SHORT).show();
    }
}
