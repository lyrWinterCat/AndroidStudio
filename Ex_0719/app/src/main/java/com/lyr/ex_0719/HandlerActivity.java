package com.lyr.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerActivity extends AppCompatActivity {
    
    TextView txt_count;
    Button btn_start, btn_stop,btn_reset;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        
        txt_count = findViewById(R.id.text_count);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_reset = findViewById(R.id.btn_reset);

        btn_start.setOnClickListener(click);
        btn_stop.setOnClickListener(click);
        btn_reset.setOnClickListener(click);

        
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_start:
                    //눌렀을 시 백그라운드에서 txt_count 값을 1씩 증가시키는 핸들러 호출
                    //handler.handleMessage(); >> 백그라운드 사용이 아닌 일반 메서드처럼 동작

                    //핸들러의 handleMessage() 메서드를 호출하는 방법
                    handler.sendEmptyMessage(0); //what?

                    //버튼 비활성화 >> start를 여러번 누르면 handler가 중첩되서 속도가 빨라짐..
                    btn_start.setEnabled(false);

                    break;
                case R.id.btn_stop:
                    // 핸들러 정지
                    handler.removeMessages(0);
                    btn_start.setEnabled(true);
                    break;
                case R.id.btn_reset:
                    count=0;
                    txt_count.setText(String.valueOf(count));
                    handler.removeMessages(0);
                    btn_start.setEnabled(true);
                    break;
            }
        }
    };

    //핸들러 준비
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //백그라운드에서 코드를 실행하는 영역
            // super.handleMessage(msg);

            //handler.sendEmptyMessage(0);
            handler.sendEmptyMessageDelayed(0,1000);

            count++;
            txt_count.setText(String.valueOf(count));
        }
    };

}