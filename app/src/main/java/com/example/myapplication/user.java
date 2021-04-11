package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class user extends AppCompatActivity {
    TextView user_text,text;
    String r_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        String name[] = new String[]{"账号管理","账号安全","消息通知","检查更新","用户反馈"};
        List<Map<String,Object>> listitem = new ArrayList<Map<String,Object>>();
        for (int i =0 ;i<name.length;i++){
            Map<String,Object> map =new HashMap<String,Object>();
            map.put("name",name[i]);
            listitem.add(map);
        }
        final SimpleAdapter adapter = new SimpleAdapter(this,listitem,R.layout.list_item2,new  String[]{"name"},new int[]{R.id.ddd});
        ListView lis =findViewById(R.id.user_list);
        lis.setAdapter(adapter);
        lis.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Map<String,Object> map = (Map<String, Object>) parent.getItemAtPosition(i);
                Toast.makeText(user.this,"你选择了"+map.get("name")+",但该功能还没开发",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void zjm1(View view) {
        Intent intent = new Intent(user.this,zjm.class);
        user.this.finish();
        startActivity(intent);
    }

    public void end(View view) {
        AlertDialog alertDialog = new AlertDialog.Builder(user.this).create();
        alertDialog.setTitle("友情提示");
        alertDialog.setMessage("确认退出?");
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(user.this,MainActivity.class);
                user.this.finish();
                startActivity(intent);
                Log.i("退出成功","!!!");
            }
        });
        alertDialog.show();

    }

    public void kh2(View view) {
        Intent intent = new Intent(user.this,kehu.class);
        user.this.finish();
        startActivity(intent);
    }
}
