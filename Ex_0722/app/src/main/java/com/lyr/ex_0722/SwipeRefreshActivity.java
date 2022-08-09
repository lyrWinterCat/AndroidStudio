package com.lyr.ex_0722;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SwipeRefreshActivity extends AppCompatActivity {

    SwipeRefreshLayout swipe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh);

        swipe = findViewById(R.id.swipe);

        //디스크의 배경색 변경
        swipe.setProgressBackgroundColorSchemeColor(Color.parseColor("#aaaaff"));

        // 디스크의 사이즈 변경
        swipe.setSize(SwipeRefreshLayout.LARGE);

        //스케일을 바꿀건지, 어느시점까지 당겨지게 할건지 결정
        swipe.setProgressViewEndTarget(true,300);

        //프로그래스바 색 바꾸기
        swipe.setColorSchemeResources(R.color.mycolor1,R.color.mycolor2,R.color.mycolor3,R.color.mycolor4);

        //swipe에게 이벤트 감지자 등록
        swipe.setOnRefreshListener(swipeListener);
    }

    SwipeRefreshLayout.OnRefreshListener swipeListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            //당겼다가 손을 떼는 순간 호출되는 메서드
            //new Async().execute()
            h.sendEmptyMessageDelayed(0,5000);
        }
    };

    // 로딩을 한다고 가정하는 handler
    Handler h = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            //로딩이 완료된 시점에서 디스크 제거
            swipe.setRefreshing(false);
        }
    };





}