package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

public class wlecome extends AppCompatActivity {
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlecome);
        final Button btn1= (Button) findViewById(R.id.btn1);
        final Intent intent = new Intent(wlecome.this,MainActivity.class);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                wlecome.this.finish();
                i=1;
            }
        });
        class mytime extends CountDownTimer {

            public mytime(long millisInFuture, long countDownInterval) {
                super(millisInFuture, countDownInterval);
            }

            @Override
            public void onTick(long l) {
                btn1.setText("跳过（"+l/1000+"）");

            }

            @Override
            public void onFinish() {
                if (i==0){
                    startActivity(intent);
                    wlecome.this.finish();
                }
            }
        }
        mytime time=new mytime(5000,1000);
        time.start();
    }
}
