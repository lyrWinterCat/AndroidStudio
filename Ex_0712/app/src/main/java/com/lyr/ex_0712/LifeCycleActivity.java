package com.lyr.ex_0712;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class LifeCycleActivity extends AppCompatActivity {
// 앱 실행시 가장 먼저 실행되는 함수 : onCreate()
    // 맨 처음에는 onCreate()라는 함수가 무조건 실행됨
    // 실행 검증..?
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        Log.i("MY","--onCreate--");
        //확인하고 싶은 정보를 눈으로 보고 싶다면, Log를 사용해야 한다.
        // tag, msg는 작성하면 자동으로 나오는 것으로, 직접 쓸 필요가 없다.
        //log의 확인은 실행 후 하단 바의 Logcat으로, emulator나 핸드폰의 모든 action에 대한 기록이므로
        // (볼륩조절, 블루투스 연결 등등)
        // 돋보기에(filter) 지정한 tag를 검색하여 확인하는 것이 빠르다.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MY","--onDestroy--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MY","--onPause--");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("My","--onStart--");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Toast 사용법
        //context : 화면 제어권자 / 현재 activity를 적어주는 경우가 많음
        // text : 화면에 나올 내용
        // spring에서의 alert과 같은 기능. 어떤 활동중인지 알 수 있음
        // Toast. 함수로 인해 여러가지 기능을 테스트해 볼 수 있음
        Toast.makeText(LifeCycleActivity.this, "재실행", Toast.LENGTH_LONG).show();
        Log.i("MY","--onRestart--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MY","--onResume--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MY","--onStop--");
    }
    /*
    최초로 앱을 실행한 경우  :
    onCreate() -- 딱 한 번만 호출
    onStart()
    onResume()

    홈버튼을 눌렀을 때 앱을 빠져나간 경우 :
    onPause()
    onStop()

    홈버튼을 누른 후 다시 앱으로 복귀했을 때
    onRestart()
    onStart()
    onResume()

    뒤로가기 버튼을 눌러서 앱을 완전히 종료했을 때
    onPause()
    onStop()
    onDestroy() -- 딱 한 번만 호출
     */
}