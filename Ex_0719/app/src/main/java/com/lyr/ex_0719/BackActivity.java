package com.lyr.ex_0719;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class BackActivity extends AppCompatActivity {

    //boolean checkFlag = false;
    int num = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
    }

    @Override
    public void onBackPressed() {
        //뒤로가기 클릭시 3초 안으로 두번 누르면 종료, 한번 누르고 3초 후 누르면 종료 못함
/*        if(!checkFlag){
            Toast.makeText(BackActivity.this, "한번 더 눌러야 종료가 됩니다.", Toast.LENGTH_SHORT).show();

            handler.sendEmptyMessage(0);
        }else{
            handler.sendEmptyMessage(1);
        }*/

        if(num!=3){
            finish();
        }else{
            Toast.makeText(BackActivity.this, "한번 더 눌러야 종료가 됩니다.", Toast.LENGTH_SHORT).show();
            //핸들러 호출
            handler.sendEmptyMessage(0);
        }
    }

   /* Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
               switch(msg.what){
                case 0:
                    handler.sendEmptyMessageDelayed(0,3000);
                    checkFlag=true;
                    break;
                case 1:
                    finish();
                    break;
            }
        }
    };*/

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //2초 카운팅을 위한 핸들러
            sendEmptyMessageDelayed(0,1000);
            if(num>0){
                --num;
            }else{
                num=3;
                handler.removeMessages(0);// remove가 없다면 2,1,0,2,1,0 계속 반복하게 됨
            }
        }
    };


}