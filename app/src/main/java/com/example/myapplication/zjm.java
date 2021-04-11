package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class zjm extends AppCompatActivity {
    //需要适配的数据
    Button btn;
    private String[] titles={"桌子","苹果","蛋糕","线衣","猕猴桃","围巾"};
    private String[] prices={"1800元","10元/kg","300元","350元","10元/kg","280元"};
    private  int[] icons={R.drawable.table,R.drawable.apple,R.drawable.cake,
            R.drawable.wearclothes,R.drawable.kiwifruit,R.drawable.scarf};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zjm);
        ListView listView =findViewById(R.id.lv);
        MyBaseAdapter mAdapter=new MyBaseAdapter();
        listView.setAdapter(mAdapter);
    }


    class MyBaseAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView==null){
                convertView=View.inflate(zjm.this,R.layout.list_item, null);
                holder=new ViewHolder();
                holder.title=convertView.findViewById(R.id.title);
                holder.price=convertView.findViewById(R.id.price);
                holder.iv=convertView.findViewById(R.id.iv);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }
            holder.title.setText(titles[position]);
            holder.price.setText(prices[position]);
            holder.iv.setImageResource(icons[position]);
            return convertView;
        }
    }
    class ViewHolder{
        TextView title;
        TextView price;
        ImageView iv;
    }

    public void user1(View view) {
        Intent intent = new Intent(zjm.this,user.class);
        zjm.this.finish();
        startActivity(intent);
    }
    public void kh2(View view) {
        Intent intent = new Intent(zjm.this,kehu.class);
        zjm.this.finish();
        startActivity(intent);
    }

}
