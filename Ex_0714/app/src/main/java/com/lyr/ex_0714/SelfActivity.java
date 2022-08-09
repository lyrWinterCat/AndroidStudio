package com.lyr.ex_0714;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SelfActivity extends AppCompatActivity {

    Button btn_send;
    EditText eTxt;
    TextView vTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self);

        btn_send = findViewById(R.id.btn_send);
        eTxt = findViewById(R.id.et_num);
        vTxt = findViewById(R.id.tv_result);


        btn_send.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) { // View 클래스 > java에서 Object 클래스
            switch (view.getId()){ // view : 현재 클릭된 개체
                case R.id.btn_send:
                    // 그냥 string으로 받아서, string끼리 비교할 수도 있음
                    // 난...숫자만 하는줄 알았지....
                    int num = Integer.parseInt(eTxt.getText().toString());
                    String temp = "";
                    for (int i=eTxt.getText().length()-1; i>=0; i--){
                        temp+=eTxt.getText().charAt(i);
                    }
                    int reverse = Integer.parseInt(temp);
                    if(num==reverse){
                        vTxt.setText(eTxt.getText()+" = 회문수");
                        eTxt.setText("");
                    } else{
                        vTxt.setText(eTxt.getText()+"= 회문수X");
                        eTxt.setText("");
                    }
                    break;
            }
        }
    };


}