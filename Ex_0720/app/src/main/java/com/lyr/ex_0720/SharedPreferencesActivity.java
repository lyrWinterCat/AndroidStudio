package com.lyr.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {

    // 간단한 정보를 저장해야 하는 경우 DB를 쓰기에 오히려 손해인 경우가 있음
    // 내부적으로 파일형태로 기록되는 저장방식을 통해 휴대폰이 종료되거나 재시작되었어도
    // 앱을 삭제하기 전까지 남아있는 데이터를 만들 수 있음
    SharedPreferences pref;

    TextView txt_view;
    Button btn_plus, btn_minus, btn_reset;
    int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        
        pref = getSharedPreferences("SHARE",MODE_PRIVATE);
        // 2가지 파라미터를 받는데, name은 자유로워도 되나 공식문서는 share로 되어 있음
        // private : 보이지 않게 숨겨서 받겠다

        //앱을 실행했을 때 save라는 이름으로 저장해둔 num값을 읽어온다
        num = pref.getInt("save",0);

        txt_view = findViewById(R.id.txt_value);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_reset = findViewById(R.id.btn_reset);

        btn_plus.setOnClickListener(click);
        btn_minus.setOnClickListener(click);
        btn_reset.setOnClickListener(click);

        txt_view.setText(""+num);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            num = Integer.parseInt(txt_view.getText().toString());
            switch(view.getId()){
                case R.id.btn_plus:
                    //handler.sendEmptyMessage(0);
                    num ++;
                    txt_view.setText(String.valueOf(num));
                    break;
                case R.id.btn_minus:
                    //handler.sendEmptyMessage(1);
                    num --;
                    txt_view.setText(""+num);
                    break;
                case R.id.btn_reset:
                    txt_view.setText(String.valueOf(0));
                    //handler.sendEmptyMessage(2);
                    break;
            }
        }
    };

    //애플리케이션이 일시정지 상태일 때 n값을 저장
    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("save",num);
        edit.commit(); //물리적으로 세이브 데이터를 저장 >>파일탐색기로는 찾을 수 없음
    }

    //왜썼징
    /*Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            value = Integer.parseInt(txt_view.getText().toString());
            switch(msg.what){
                case 0:
                    value ++;
                    txt_view.setText(String.valueOf(value));
                    break;
                case 1:
                    value --;
                    txt_view.setText(String.valueOf(value));
                    break;
                case 2:
                    txt_view.setText(String.valueOf(0));
                    break;
            }
        }
    };*/


}