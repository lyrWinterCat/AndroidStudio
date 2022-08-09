package com.lyr.ex_0718;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentSubActivity extends AppCompatActivity {

    Button btn_preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_sub);

        btn_preview = findViewById(R.id.btn_preview);

        btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(IntentSubActivity.this,IntentActivity.class);

                //중복된 페이지를 걸러내는 플래그 추가
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                // 맨 위 액티비티 제거 |  똑같은 이름의 액티비티가 살아있을 때 해당 액티비티 소환 >>겹치기 막음

                startActivity(i);
                //finish();
            }
        });
    }
}