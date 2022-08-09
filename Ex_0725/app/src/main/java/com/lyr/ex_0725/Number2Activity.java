package com.lyr.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Number2Activity extends AppCompatActivity {

    TextView text_num01,text_q;
    Button btn_yes, btn_no, btn_restart;

    //결과 출력 변수
    int result=0;

    //숫자패널 변경용 변수
    int phase=1;


    final int YES = 1;
    final int NO = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number2);

        text_num01 = findViewById(R.id.text_num01);

        text_q = findViewById(R.id.txt_q);

        btn_yes = findViewById(R.id.btn_yes);
        btn_no = findViewById(R.id.btn_no);
        btn_restart = findViewById(R.id.btn_restart);

        btn_yes.setOnClickListener(click);
        btn_no.setOnClickListener(click);
        btn_restart.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_yes:
                    showPhase(YES);
                    break;
                case R.id.btn_no:
                    showPhase(NO);
                    break;
                case R.id.btn_restart:
                    phase=1;
                    result=0;
                    text_num01.setText("04 05 06 07 12 \n13 14 15 20 21 \n22 23 28 29 30");
                    text_q.setVisibility(View.VISIBLE);
                    btn_yes.setVisibility(View.VISIBLE);
                    btn_no.setVisibility(View.VISIBLE);
                    btn_restart.setVisibility(View.GONE);

                    break;
            }
        }
    };

    // phase를 넘겨주는 메서드
    public void showPhase(int select){
        String str = "";
        switch (phase){
            case 1:
                if(select==YES){
                    result += 4;
                }
                str="16 17 18 19 20 \n21 22 23 24 25 \n26 27 28 29 30";

                break;
            case 2:
                if(select==YES){
                    result += 16;
                }
                str="01 03 05 07 09 \n11 13 15 17 19 \n21 23 25 27 29";

                break;
            case 3:
                if(select==YES){
                    result += 1;
                }
                str="08 09 10 11 12 \n13 14 15 24 25 \n26 27 28 29 30";

                break;
            case 4:
                if(select==YES){
                    result += 8;
                }
                str="02 03 06 07 10 \n11 14 15 18 19 \n22 23 26 27 30";

                break;
            case 5:
                if(select==YES){
                    result += 2;
                }
                //결과가 0이거나 31인 경우엔 잘못 누른 항목이 있다는 것
                if(result==0 || result>=31){
                    str="잘 못 선택한 문항이 있습니다.\n 다시 시도해보세요.";
                }else  {
                    str="당신이 생각한 숫자는\n"+result+"입니다.";
                }
                text_q.setVisibility(View.GONE);
                btn_yes.setVisibility(View.GONE);
                btn_no.setVisibility(View.GONE);
                btn_restart.setVisibility(View.VISIBLE);
                break;
        }
        text_num01.setText(str);
        phase++;
    }

}