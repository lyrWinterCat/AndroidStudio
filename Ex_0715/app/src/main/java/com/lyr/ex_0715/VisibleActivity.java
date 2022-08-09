package com.lyr.ex_0715;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VisibleActivity extends AppCompatActivity {

    Button btn_back1, btn_back2, btn_back3, bot;
    ImageView img_back1, img_back2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visible);

        btn_back1 = findViewById(R.id.btn_back1);
        btn_back2 = findViewById(R.id.btn_back2);
        btn_back3 = findViewById(R.id.btn_back3);
        bot = findViewById(R.id.bot);

        img_back1 = findViewById(R.id.img_back1);
        img_back2 = findViewById(R.id.img_back2);

        btn_back1.setOnClickListener(click);
        btn_back2.setOnClickListener(click);
        btn_back3.setOnClickListener(click);

    }

    /*
     감지자로 처리하는 방법
     1. id로 비교하기 >> view.getId()
     2. view를 자식클래스로 캐스팅해버리기
     3. 이번에 쓰는 방법 : 묶어서 감지자 처리
     >> 인스턴스(객체)가 같기 때문에 그냥 비교해버리기
     (주소값이 같기 때문에 가능 ! )
     */

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(btn_back1 == view){
                //back1을 눌렀을 시 비행기 보이고 동그라미 안보임
                img_back1.setVisibility(View.VISIBLE);
                img_back2.setVisibility(View.INVISIBLE);
            }
            if(btn_back2 == view){
                img_back2.setVisibility(View.VISIBLE);
                img_back1.setVisibility(View.INVISIBLE);
            }
            if(btn_back3 == view){
                if(bot.getVisibility() != View.VISIBLE){
                    bot.setVisibility(View.VISIBLE);
                }else {
                    bot.setVisibility(View.GONE);
                }
            }
        }
    };
}