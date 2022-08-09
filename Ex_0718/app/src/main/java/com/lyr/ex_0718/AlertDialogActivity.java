package com.lyr.ex_0718;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AlertDialogActivity extends AppCompatActivity {

    Button btn_show;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);

        btn_show = findViewById(R.id.btn_show);
        btn_show.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // () 안에 어떤 요소가 필요한지 궁금하다면 ctrl + P
                // context가 필요하다고 하면 현재 있는 클래스명.this
                AlertDialog.Builder dialog = new AlertDialog.Builder(AlertDialogActivity.this);

                dialog.setTitle("앱 알림창");
                dialog.setMessage("평가좀 해주세뇽...");

                //다이얼로그에 버튼 추가 - positive, negative, nature >> 버튼 이름에 따라 위치 고정
                // 테마에 따라 다름
                // 이미 존재하는 버튼을 만들면 해당 버튼에 덮어씌움

                dialog.setPositiveButton("조아용", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertDialogActivity.this,"좋아요클릭",Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.setNegativeButton("싫어용", null);

                dialog.setNeutralButton("다음에 할게용",null);

                //다이얼로그가 뒤로가기나 빈공간 터치로 사라지는 것을 방지하는 코드
                // dialog 내의 세 버튼 중 하나를 눌러야만 꺼질수 있도록
                dialog.setCancelable(false);

                //앱을 종료할 때 예/아니오 둘 중 하나는 반드시 눌러야 하니까
                // 위와 같은 경우 사라지는 것을 방지하도록 하는 장치로 사용할 수도 있음


                dialog.show();

            }
        });

    }//oncreate

    //oncreate 안과 밖에 작성하는 것의 구별?? >> 많이 해봐야 깨달음
    // 메서드 > onCreate 밖에 작성 (함수는 함수 안에 만들지 못함)
    // 생명주기에 관련된 메서드 : onCreate 밖에 작성

    @Override
    public void onBackPressed() { //뒤로가기를 클릭했을 시 호출되는 메서드
        //super.onBackPressed(); //override한 메서드의 super를 지우면 에러날 수도 있음
        // 단, onBackPressed는 예외 ^^

        //Toast.makeText(AlertDialogActivity.this,"뒤로가기 누름",Toast.LENGTH_SHORT).show();

        // 여기서 종료하기 다이얼로그를 만들 수 있음
        AlertDialog.Builder dialog2 = new AlertDialog.Builder(AlertDialogActivity.this);

        dialog2.setTitle("앱 종료 확인");
        dialog2.setMessage("앱을 종료하시겠습니까?");

        dialog2.setPositiveButton("아니용", null);

        dialog2.setNegativeButton("넹", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Toast.makeText(AlertDialogActivity.this,"앱 종료 클릭",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        dialog2.setCancelable(false);

        dialog2.show();


    }
}