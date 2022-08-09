package com.lyr.ex_0720;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class InflaterActivity extends AppCompatActivity {

    Button btn1;
    LinearLayout layout;
    View inner;

    //xml문서를 view구조로 캐스팅해주는 객체
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inflater);

        layout=findViewById(R.id.layout);

        inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        inner = inflater.inflate(R.layout.my_inflater,layout);
        btn1 = inner.findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(InflaterActivity.this, "안쪽 레이아웃", Toast.LENGTH_SHORT).show();
            }
        });

        //LinearLayout 안쪽에 inner라고 생성해 둔 my_inflater 추가
        //layout.addView(inner); >> 생략 후 inner = inflater.inflate(R.layout.my_inflater, null) 에서
        // null 대신 layout을 넣어주어도 됨
        // 대신 위에 layout 을 findViewById로 찾아 준 다음에 해 주어야 함

    }
}