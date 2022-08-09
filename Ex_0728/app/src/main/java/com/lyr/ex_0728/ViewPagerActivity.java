package com.lyr.ex_0728;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;

public class ViewPagerActivity extends FragmentActivity {

    ViewPager pager;
    Button menu1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        pager = findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        //밑줄을 그려주는 인디케이터
        UnderlinePageIndicator under = findViewById(R.id.indicator);
        under.setFades(false);
        //under.setViewPager(pager);

        CirclePageIndicator circle = findViewById(R.id.circle);
        circle.setViewPager(pager);

        menu1 = findViewById(R.id.menu1);
        menu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pager.setCurrentItem(0);//버튼을 눌렀을 때 포지션 0번째 화면으로 전환
            }
        });


    }
}