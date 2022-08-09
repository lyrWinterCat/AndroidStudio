package com.lyr.ex_0718;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity { //activity 간의 화면 전환을 위해 만들어진 클래스

    Button btn_call, btn_sms, btn_camera, btn_gallery, btn_link, btn_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        btn_call = findViewById(R.id.btn_call);
        btn_sms = findViewById(R.id.btn_sms);
        btn_camera = findViewById(R.id.btn_camera);
        btn_gallery = findViewById(R.id.btn_gallery);
        btn_link = findViewById(R.id.btn_link);
        btn_next = findViewById(R.id.btn_next);

        // 공간 마련 , 커서, alt shift + 입력
        btn_call.setOnClickListener(click);
        btn_sms.setOnClickListener(click);
        btn_camera.setOnClickListener(click);
        btn_gallery.setOnClickListener(click);
        btn_link.setOnClickListener(click);
        btn_next.setOnClickListener(click);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //화면전환을 위해 반드시 필요한 클래스
            Intent i = null;

            switch (view.getId()){
                case R.id.btn_call:
                    /*i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:010-111-1111"));
                    startActivity(i);*/

                    //자동으로 전화걸기 >>권한 필요
                    i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:010-111-1111"));
                    startActivity(i);

                    break;

                case R.id.btn_sms:
                    i = new Intent(Intent.ACTION_SENDTO);
                    // smsto : 문자를 보낼 전화번호 지정
                    i.setData(Uri.parse("smsto:010-2222-2222"));
                    //putExtra를 사용하면 보낼 문자의 내용을 지정해 줄 수 있음
                    //key, value로 저장을 해야 함.
                    // key값은 sms_body로 고정, value는 보낼 문자 내용
                    i.putExtra("sms_body","앱을 사용해주셔서 감사합니다");
                    startActivity(i);
                    break;

                case R.id.btn_camera:
                    i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(i);

                    //동영상 연결
                    i = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                    startActivity(i);
                    break;

                case R.id.btn_gallery:
                    i = new Intent(Intent.ACTION_GET_CONTENT);
                    i.setType("*/*");
                    startActivity(i);
                    break;

                case R.id.btn_link:
                    /*i = new Intent(Intent.ACTION_VIEW);
                    // action_view가 없어도 동작을 하지만,
                    // 낮은 버전에서 사용하면 무조건 구글로 가는 버그가 있음
                    i.setData(Uri.parse("https://naver.com"));
                    startActivity(i);*/

                    //플레이스토어로 이동하는법
                    i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("market://details?id=com.lyr.ex_0718"));
                    startActivity(i);
                    break;

                case R.id.btn_next:
                    i = new Intent(IntentActivity.this,IntentSubActivity.class);
                    startActivity(i);
                    //finish();
                    break;
            }


        }
    };
}