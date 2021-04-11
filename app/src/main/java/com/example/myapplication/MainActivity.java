package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et_user_name,et_psw;//获取文本框内容
    private Button dl_btn;//获取登录按钮
    private TextView tv_register,tv_find_psw;//获取账号密码信息
    private String userName,psw,spPsw;//获取的用户名，密码，加密密码
    private MyBRREceceiver brrEceceiver;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(brrEceceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_user_name = findViewById(R.id.dl_user);
        et_psw = findViewById(R.id.dl_pword);
        dl_btn=findViewById(R.id.btn1);
        tv_register = (TextView) findViewById(R.id.tv_register);
        tv_find_psw = (TextView) findViewById(R.id.tv_find_psw);

        //广播
        brrEceceiver =new MyBRREceceiver();//初始化广播接收者
        IntentFilter intentFilter = new IntentFilter();//初始化IntentFilter
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(brrEceceiver,intentFilter);//注册广播



        dl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开始登录，获取用户名和密码 getText().toString().trim();
                userName = et_user_name.getText().toString().trim();
                psw = et_psw.getText().toString().trim();
                spPsw = readPsw(userName);
                if (TextUtils.isEmpty(userName)) {
                    Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                    // md5Psw.equals(); 判断，输入的密码，是否与保存在SharedPreferences中一致
                } else if (psw.equals(spPsw)) {
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //保存登录状态，在界面保存登录的用户名 定义个方法 saveLoginStatus boolean 状态 , userName 用户名;
                    saveLoginStatus(true, userName);
                    //登录成功后关闭此页面进入主页
                    Intent data = new Intent();
                    //datad.putExtra( ); name , value ;
                    data.putExtra("isLogin", true);
                    setResult(RESULT_OK, data);
                    //销毁登录界面
                    MainActivity.this.finish();
                    //跳转到主界面，登录成功的状态传递到 MainActivity 中
                    startActivity(new Intent(MainActivity.this, zjm.class));
                } else if ((spPsw != null && !TextUtils.isEmpty(spPsw) && !psw.equals(spPsw))) {
                    Toast.makeText(MainActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "此用户名不存在", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private String readPsw(String userName){
        //创建并读取数据
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //sp.getString() userName, "";
        return sp.getString(userName , "");
    }

    private void saveLoginStatus(boolean status,String userName){
        //loginInfo表示文件名  SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取编辑器
        SharedPreferences.Editor editor=sp.edit();
        //存入boolean类型的登录状态
        editor.putBoolean("isLogin", status);
        //存入登录状态时的用户名
        editor.putString("loginUserName", userName);
        //提交修改
        editor.apply();
    }

    public void zhuce(View view) {
        Intent intent = new Intent(MainActivity.this,zhuce.class);
        startActivityForResult(intent, 1);
    }
}
