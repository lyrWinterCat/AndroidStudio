package com.lyr.ex_0725;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ajalt.reprint.core.AuthenticationFailureReason;
import com.github.ajalt.reprint.core.AuthenticationListener;
import com.github.ajalt.reprint.core.Reprint;

public class FingerPrintActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
        
        text = findViewById(R.id.text);
        
        //지문사용을 위한 초기화

        Reprint.initialize(FingerPrintActivity.this);
        if(checkSpec()){
            //지문인식이 가능한 경우
            Reprint.authenticate(new AuthenticationListener() {
                @Override
                public void onSuccess(int moduleTag) {
                    //보통은 텍스트만 바꾸는 데에 끝나지 않고 화면을 전환한다.
                    text.setText("인증 성공~~");
                }

                @Override
                public void onFailure(AuthenticationFailureReason failureReason, boolean fatal, CharSequence errorMessage, int moduleTag, int errorCode) {
                    text.setText("인증 실패...");
                }
            });
        }
    }//onCreate()

    //지문인식이 가능한지 판단
    public boolean checkSpec(){
        //참이면 지문인식 센서가 존재한다는 것
        boolean hardware = Reprint.isHardwarePresent();

        //센서가 있어도 지문이 등록이 안되어있다면 사용을 할 수가 없기 때문에 등록되어있는 지문을 확인해보아야 함
        boolean register = Reprint.hasFingerprintRegistered();
        if(!hardware){
            Toast.makeText(FingerPrintActivity.this, "지문인식을 지원하지 않는 기기입니다.", Toast.LENGTH_SHORT).show();
        }else{
            if(!register){
                Toast.makeText(FingerPrintActivity.this, "등록된 지문이 없습니다.\n지문등록을 먼저 진행해주세요", Toast.LENGTH_SHORT).show();
            }
        }

      return hardware && register;
    };

}