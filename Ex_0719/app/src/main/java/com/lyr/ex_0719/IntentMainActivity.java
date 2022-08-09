package com.lyr.ex_0719;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class IntentMainActivity extends AppCompatActivity {

    EditText edit_name, edit_age, edit_tel, edit_b_day;
    Button btn_date, btn_send;
    Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_main);

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_tel = findViewById(R.id.edit_tel);
        edit_b_day = findViewById(R.id.edit_b_day);

        btn_date = findViewById(R.id.btn_date);
        btn_send = findViewById(R.id.btn_send);
        
        btn_send.setOnClickListener(send_click);
        btn_date.setOnClickListener(date_click);



    }

    View.OnClickListener send_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //화면 전환 시 파라미터로 전달할 값들
            String name = edit_name.getText().toString();
            String age = edit_age.getText().toString();
            String tel = edit_tel.getText().toString();
            String date = edit_b_day.getText().toString();
            
            //유효성검사
            if(name.trim().length()==0){
                Toast.makeText(IntentMainActivity.this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            }           

            /*if(age.trim().length()==0){
                Toast.makeText(IntentMainActivity.this, "나이을 입력하세요", Toast.LENGTH_SHORT).show();
            } */

            /*if(tel.trim().length()==0){
                Toast.makeText(IntentMainActivity.this, "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
            }*/
            
            Intent i = new Intent(IntentMainActivity.this,IntentSubActivity.class);
            
            //map형식으로 값 넘기기
            i.putExtra("m_name",name);
            i.putExtra("m_age",age);
            i.putExtra("m_tel",tel);
            i.putExtra("m_date",date);
            
            startActivity(i);
            
        }
    };

    final View.OnClickListener date_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //달력에 최초로 표기될 날짜를 구한다
            Calendar now = Calendar.getInstance();
            int y = now.get(Calendar.YEAR);

            // 7월은 6으로 넘어옴
            // 1-12월 >> 0-11로 들어오기 때문
            int m = now.get(Calendar.MONTH);
            int d = now.get(Calendar.DAY_OF_MONTH);


            dialog = new DatePickerDialog(IntentMainActivity.this,
                    dateSetListener,//감지자
                    y, m, d);
            dialog.show();
        }
    };

    //달력의 변경사항을 감지하는 이벤트 감지자
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int y, int m, int d) {
            String result = String.format("%d-%02d-%02d",y,m+1,d);
            edit_b_day.setText(result);
        }
    };


}