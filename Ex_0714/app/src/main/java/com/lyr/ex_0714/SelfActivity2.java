package com.lyr.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class SelfActivity2 extends AppCompatActivity {

    TextView tvBox;
    Button btn1,btn2,btn3,btn4,btn_R;

    int num = new Random().nextInt(4)+1;
    int rnd;

    boolean win;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self2);

        tvBox = findViewById(R.id.tv_result);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn_R = findViewById(R.id.btn_restart);

        setRandom();

        btn1.setOnClickListener(click);
        btn2.setOnClickListener(click);
        btn3.setOnClickListener(click);
        btn4.setOnClickListener(click);

        btn_R.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvBox.setText("랜덤게임");
                setRandom();
            }
        });


    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // 클릭된 버튼의 text를 가져오는 방법으로 버튼 판별 >>btn1.getText()
            // view.getText() >> 불가능 : 자바의 상속 개념
            // 안드로이드에서는 가능! (부모가 자식의 메서드를 가져다 쓸 수 있음 ! )

            int str = Integer.parseInt(((Button)view).getText().toString());
            if(str == rnd){
                //버튼의 쓰여진 텍스트와 랜덤값인 rnd가 같다면 당첨
                tvBox.setText("당첨입니당");
            }else{
                tvBox.setText("꽝입니당");
            }

            //switch문 활용
            /*
            * switch (view.getId()){ // view : 현재 클릭된 개체
                case R.id.btn_1:
                    if(num==1){
                        win=true;
                    }else{
                        win=false;
                    }
                    check();
                    break;
                case R.id.btn_2:
                    if(num==2){
                        win=true;
                    }else{
                        win=false;
                    }
                    check();
                    break;
                case R.id.btn_3:
                    if(num==3){
                        win=true;
                    }else{
                        win=false;
                    }
                    check();
                    break;
                case R.id.btn_4:
                    if(num==4){
                        win=true;
                    }else{
                        win=false;
                    }
                    check();
                    break;
                case R.id.btn_restart:
                    num = new Random().nextInt(4)+1;
                    tvBox.setText("랜덤게임");
                    break;
            }
            * */
        }
    };

    public void setRandom(){
        rnd = new Random().nextInt(4)+1;
    }


    /*
    * public void check(){
      if(win){
          tvBox.setText("당첨입니다");
      }else{
          tvBox.setText("꽝입니당");
      }
    }
    * */




}