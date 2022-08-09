package com.lyr.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EventActivity extends AppCompatActivity {

    //변수명. layout의 id와 상관없음. 통일시켜도 됨
    // 레이아웃에서 객체로 사용하는 모든 것들은 액티비티에서 클래스로 존재함
    Button b_red, b_green, b_blue, b_send, b_reset;
    TextView txt;
    EditText etxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        // 이벤트 처리에 사용할 객체를 '검색'
        //b_red = new Button(this); >>이렇게 선언하지 않음.
        // 레이아웃에 있는 객체들은 이미 메모리에 할당이 되어있기 때문에 new로 만들 수 없음
        b_red = findViewById(R.id.btn_red);
        b_green = findViewById(R.id.btn_green);
        b_blue = findViewById(R.id.btn_blue);
        txt = findViewById(R.id.txt);
        etxt = findViewById(R.id.et);
        b_send = findViewById(R.id.btn_send);
        b_reset = findViewById(R.id.btn_reset);

        //버튼 클릭 시 이벤트 처리
        b_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 호출되는 영역
                //txt.setBackgroundColor(Color.RED);
                if(txt.getText().equals("결과")){
                    txt.setBackgroundColor(Color.parseColor("#F44336"));
                    txt.setText("RED");
                } else if(!(txt.getText().equals("결과"))){
                    txt.setBackgroundColor(Color.BLACK);
                    txt.setText("결과");
                }
            }
        });

        b_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 호출되는 영역
                //txt.setBackgroundColor(Color.RED);
                txt.setBackgroundColor(Color.parseColor("#4CAF50"));
                txt.setText("GREEN");
            }
        });

        b_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 호출되는 영역
                //txt.setBackgroundColor(Color.RED);
                txt.setBackgroundColor(Color.parseColor("#2196F3"));
                txt.setText("BLUE");
            }
        });
/*

       b_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText(etxt.getText());
            }
        });

        b_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setBackgroundColor(Color.BLACK);
                txt.setText("결과");
            }
        });
* */
         b_send.setOnClickListener(click);
         b_reset.setOnClickListener(click);

    }//onclick

    //OnClickListener 인터페이스는 안드로이드에서 new로 생성이 가능
    // >> 감지자
    // 감지자는 하나, 버튼마다 다른 이벤트를 만들고 싶다면?? >> switch!
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // View 클래스 > java에서 Object 클래스
            //Toast.makeText(EventActivity.this,"감지자입니당",Toast.LENGTH_SHORT).show();
            switch (view.getId()){ // view : 현재 클릭된 개체
                case R.id.btn_send:
                    String str = etxt.getText().toString();
                    // etxt.getText()의 반환형이 Editable이기 때문에, .toString()으로 형변환 필요
                    txt.setText(str);
                    etxt.setText("");
                    break;
                case R.id.btn_reset:
                    txt.setBackgroundColor(Color.BLACK);
                    txt.setText("결과");
                    break;
            }


        }
    };





}