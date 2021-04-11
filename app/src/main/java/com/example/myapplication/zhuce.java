package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class zhuce extends AppCompatActivity {
    //用户名，密码，再次输入的密码的控件
    private EditText et_user_name,et_psw,et_psw_again;
    //用户名，密码，再次输入的密码的控件的获取值
    private String userName,psw,pswAgain;
    private RadioGroup Sex;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);

        btn_register = (Button) findViewById(R.id.btn1);
        et_user_name= (EditText) findViewById(R.id.et_user_name);
        et_psw= (EditText) findViewById(R.id.et_psw);
        et_psw_again= (EditText) findViewById(R.id.et_psw_again);
        Sex= (RadioGroup) findViewById(R.id.SexRadio);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=et_user_name.getText().toString().trim();
                psw=et_psw.getText().toString().trim();
                pswAgain=et_psw_again.getText().toString().trim();
                int sex;
                int sexChoseId = Sex.getCheckedRadioButtonId();
                switch (sexChoseId) {
                    case R.id.mainRegisterRdBtnFemale:
                        sex = 0;
                        break;
                    case R.id.mainRegisterRdBtnMale:
                        sex = 1;
                        break;
                    default:
                        sex = -1;
                        break;
                }

                //判断文本框是否为空
                if(TextUtils.isEmpty(userName)){
                    Toast.makeText(zhuce.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(psw)){
                    Toast.makeText(zhuce.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(pswAgain)) {
                    Toast.makeText(zhuce.this, "请再次输入密码", Toast.LENGTH_SHORT).show();
                } else if (sex<0){
                    Toast.makeText(zhuce.this, "请选择性别", Toast.LENGTH_SHORT).show();
                }else if(!psw.equals(pswAgain)){
                    //判断输入的密码两次想不想同
                    Toast.makeText(zhuce.this, "输入两次的密码不一样", Toast.LENGTH_SHORT).show();
                }else if(isExistUserName(userName)){
                    Toast.makeText(zhuce.this, "此账户名已经存在", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(zhuce.this, "注册成功", Toast.LENGTH_SHORT).show();
                    save(userName, psw);
                    Intent data = new Intent();
                    data.putExtra("userName", userName);
                    setResult(RESULT_OK, data);
                    zhuce.this.finish();
                }
            }
        });

    }

    /**
     * 从SharedPreferences中读取输入的用户名，判断SharedPreferences中是否有此用户名
     */
    private boolean isExistUserName(String userName){
        boolean has_userName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        //获取密码
        String spPsw=sp.getString(userName, "");//传入用户名获取密码
        //如果密码不为空则确实保存过这个用户名
        if(!TextUtils.isEmpty(spPsw)) {
            has_userName=true;
        }
        return has_userName;
    }

    private void save(String userName,String psw){
        SharedPreferences sp=getSharedPreferences("loginInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName, psw);
        editor.apply();
    }

    public void fanhui(View view) {
        Intent intent = new Intent(zhuce.this,MainActivity.class);
        startActivityForResult(intent, 1);
    }

}
