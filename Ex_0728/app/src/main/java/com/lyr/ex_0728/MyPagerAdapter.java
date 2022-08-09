package com.lyr.ex_0728;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    // 지금 사용자가 몇 번째 화면에 포커스가 넘어가있는지 포지션을 넘겨라.
    public Fragment getItem(int i) {
        Page fragment = new Page();
        fragment.setPosition(i);
        return fragment;
    }

    @Override
    public int getCount() {
        return 4; // 총 페이지 수를 지정해주는 메서드
    }
}
