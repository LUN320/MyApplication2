package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kehu extends AppCompatActivity {
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kehu);
        int pic[] = new int[]{
                R.drawable.img01,R.drawable.img02,R.drawable.img03,
                R.drawable.img04,R.drawable.img05,R.drawable.img06,
                R.drawable.img07,R.drawable.img08,R.drawable.img09,
        };
        String name[] =new String[]{
                "小米","小连","米娜",
                "小杰","土豪","少兰",
                "大米","高粱","我",
        };
        List<Map<String,Object>> listitme =new ArrayList<Map<String,Object>>();
        for (int i= 0; i<pic.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",pic[i]);
            map.put("name",name[i]);
            listitme.add(map);
        }
        final SimpleAdapter adapter = new SimpleAdapter(this,listitme,R.layout.list_item3,new String[]{"image","name"},new int[]{R.id.image_list,R.id.text_list});
        ListView lis = findViewById(R.id.listv);
        lis.setAdapter(adapter);

//        sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String str =parent.getSelectedItem().toString();
//                Toast.makeText(kehu.this,"你选择了"+str+"但是该功能未开放",Toast.LENGTH_LONG).show();
//            }
//        });

    }

    public void zjm2(View view) {
        Intent intent = new Intent(kehu.this,zjm.class);
        kehu.this.finish();
        startActivity(intent);
    }

    public void user2(View view) {
        Intent intent = new Intent(kehu.this,user.class);
        kehu.this.finish();
        startActivity(intent);
    }
}
