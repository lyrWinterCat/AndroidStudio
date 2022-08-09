package com.lyr.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;

import java.util.regex.Pattern;

public class CalActivity extends AppCompatActivity {

    Button[] numbers; //0~9숫자버튼

    // + - * / = C 버튼
    Button plus, minus, multi, div, equal, clear;

    TextView txt_result;

    String resultStr="";
    String progressStr="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        txt_result = findViewById(R.id.txt_result);

        numbers = new Button[10];
        for(int i=0; i<numbers.length; i++){
            numbers[i]=findViewById(getResources().getIdentifier("btn"+i,"id",getPackageName()));
            numbers[i].setOnClickListener(numberClick);
        }

        plus = findViewById(R.id.btn10);
        minus = findViewById(R.id.btn11);
        multi = findViewById(R.id.btn12);
        div = findViewById(R.id.btn13);
        equal = findViewById(R.id.btn14);
        clear = findViewById(R.id.btn15);

        plus.setOnClickListener(signClick);
        minus.setOnClickListener(signClick);
        multi.setOnClickListener(signClick);
        div.setOnClickListener(signClick);
        equal.setOnClickListener(signClick);
        clear.setOnClickListener(signClick);

    }

    View.OnClickListener numberClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //숫자버튼 감지
            resultStr += ((Button)view).getText().toString();
            txt_result.setText(resultStr);
            progressStr=resultStr;
        }
    };

    View.OnClickListener signClick = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //연산버튼 감지
            if(view != equal) {
                resultStr += " "+((Button)view).getText().toString()+" ";
                txt_result.setText(resultStr);
                progressStr=resultStr;

                if(view == clear){
                    resultStr = "";
                    txt_result.setText(resultStr);
                    progressStr=resultStr;
                }

            }else{
                // = 기호 클릭
                JsEvaluator jsEvaluator = new JsEvaluator(CalActivity.this);
                //evaluate  (연산하고자하는 문자열을 수식으로 변경해서 연산 결과를 반환해주는 라이브러리)
                jsEvaluator.evaluate(resultStr, new JsCallback() {
                    @Override
                    public void onResult(String s) {
                        txt_result.setText(progressStr+"\n"+s);
                    }
                });
            }

        }
    };
}