package com.lyr.ex_0727;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimActivity extends AppCompatActivity {
    Button btn_menu;
    Animation menu_visible_ani, menu_invisible_ani, rotate_ani;
    LinearLayout visible_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        btn_menu = findViewById(R.id.btn_menu);
        visible_layout = findViewById(R.id.visible_layout);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(visible_layout.getVisibility() == View.VISIBLE){
                    //현재 메뉴버튼들이 보여지고 있다 >> 숨겨라
                    menu_invisible_ani = AnimationUtils.loadAnimation(AnimActivity.this, R.anim.menu_invisible);
                    visible_layout.startAnimation(menu_invisible_ani);
                    visible_layout.setVisibility(View.INVISIBLE);
                }else {
                    // 메뉴가 숨겨져있다 >> 보여라
                    menu_visible_ani = AnimationUtils.loadAnimation(AnimActivity.this,R.anim.menu_visible);
                    visible_layout.startAnimation(menu_visible_ani);
                    visible_layout.setVisibility(View.VISIBLE);
                }

                rotate_ani=AnimationUtils.loadAnimation(AnimActivity.this,R.anim.menu_rotate);
                btn_menu.startAnimation(rotate_ani);

            }
        });

    }
}