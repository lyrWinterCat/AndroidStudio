package com.lyr.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NumberActivity extends AppCompatActivity {

    TextView text_num01,text_num02, text_num03, text_num04, text_num05, text_q,text_result;
    Button btn_yes, btn_no, btn_restart;

    int count=0;
    int result=0;
    // result가 0이거나 31이상인 경우를 check해 주어야 함...!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        text_num01 = findViewById(R.id.text_num01);
        text_num02 = findViewById(R.id.text_num02);
        text_num03 = findViewById(R.id.text_num03);
        text_num04 = findViewById(R.id.text_num04);
        text_num05 = findViewById(R.id.text_num05);
        text_result = findViewById(R.id.text_result);
        text_q = findViewById(R.id.txt_q);

        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        btn_restart=findViewById(R.id.btn_restart);

        btn_yes.setOnClickListener(click);
        btn_no.setOnClickListener(click);
        btn_restart.setOnClickListener(click);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            count++;
            switch(view.getId()){
                case R.id.btn_yes:
                    if(count==1){
                        result+=4;
                        text_num01.setVisibility(View.GONE);
                        text_num02.setVisibility(View.VISIBLE);
                    } else if(count==2){
                        result+=16;
                        text_num02.setVisibility(View.GONE);
                        text_num03.setVisibility(View.VISIBLE);
                    } else if(count==3){
                        result+=1;
                        text_num03.setVisibility(View.GONE);
                        text_num04.setVisibility(View.VISIBLE);
                    } else if(count==4){
                        result+=8;
                        text_num04.setVisibility(View.GONE);
                        text_num05.setVisibility(View.VISIBLE);
                    }else {
                        result+=2;
                        text_num05.setVisibility(View.GONE);
                        text_result.setVisibility(View.VISIBLE);
                        text_q.setVisibility(View.GONE);
                        text_result.setText("당신이 생각한 숫자는\n"+String.format("%02d",result)+"\n입니다");
                        btn_no.setVisibility(View.GONE);
                        btn_yes.setVisibility(View.GONE);
                        btn_restart.setVisibility(View.VISIBLE);
                    }

                    break;
                case R.id.btn_no:
                    if(count==1){
                        text_num01.setVisibility(View.GONE);
                        text_num02.setVisibility(View.VISIBLE);
                    } else if(count==2){
                        text_num02.setVisibility(View.GONE);
                        text_num03.setVisibility(View.VISIBLE);
                    } else if(count==3){
                        text_num03.setVisibility(View.GONE);
                        text_num04.setVisibility(View.VISIBLE);
                    } else if(count==4){
                        text_num04.setVisibility(View.GONE);
                        text_num05.setVisibility(View.VISIBLE);
                    }else {
                        text_num05.setVisibility(View.GONE);
                        text_result.setVisibility(View.VISIBLE);
                        text_q.setVisibility(View.GONE);
                        text_result.setText("당신이 생각한 숫자는\n"+String.format("%02d",result)+"\n입니다");
                        btn_no.setVisibility(View.GONE);
                        btn_yes.setVisibility(View.GONE);
                        btn_restart.setVisibility(View.VISIBLE);
                    }
                    break;

                case R.id.btn_restart:
                    count=0;
                    result=0;
                    text_num01.setVisibility(View.VISIBLE);
                    text_result.setVisibility(View.GONE);
                    btn_no.setVisibility(View.VISIBLE);
                    btn_yes.setVisibility(View.VISIBLE);
                    btn_restart.setVisibility(View.GONE);
                    break;
            }//switch
        }//onClick
    };



}