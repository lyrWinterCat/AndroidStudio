package com.lyr.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HandlerWhatActivity extends AppCompatActivity {
    
    Button btn0,btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_what);
        
        btn0 = findViewById(R.id.btn_0);
        btn1 = findViewById(R.id.btn_1);
        
        btn0.setOnClickListener(click);
        btn1.setOnClickListener(click);
        
        
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_0:
                    handler.sendEmptyMessage(0);                    
                    break;
                    
                case R.id.btn_1:
                    handler.sendEmptyMessage(1);
                    break;
            }
        }
    };
    
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            switch(msg.what){
                case 0:
                    Toast.makeText(HandlerWhatActivity.this, "0으로 호출됨", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(HandlerWhatActivity.this, "1으로 호출됨", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}