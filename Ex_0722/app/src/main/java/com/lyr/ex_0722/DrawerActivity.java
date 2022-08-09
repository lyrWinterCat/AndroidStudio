package com.lyr.ex_0722;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawerActivity extends AppCompatActivity {

    DrawerLayout drawer_layout;
    Button btn_open, btn_close;
    Button btn_close2;
    LinearLayout drawer;
    LinearLayout drawer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        drawer_layout = findViewById(R.id.drawer_layout);
        drawer =findViewById(R.id.drawer);
        drawer2 =findViewById(R.id.drawer2);
        btn_open = findViewById(R.id.btn_open);
        btn_close = findViewById(R.id.btn_close);
        btn_close2 = findViewById(R.id.btn_close2);

        btn_open.setOnClickListener(click);
        btn_close.setOnClickListener(click);
        btn_close2.setOnClickListener(click);

        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.btn_open:
                    drawer_layout.openDrawer(drawer);
                    break;
                case R.id.btn_close:
                    //drawer_layout.closeDrawer(drawer); >> drawer가 하나일때 유용
                    drawer_layout.closeDrawers(); //drawer가 여러개 있을때 닫기 작동
                    break;

                    case R.id.btn_close2:
                    //drawer_layout.closeDrawer(drawer); >> drawer가 하나일때 유용
                    drawer_layout.closeDrawers(); //drawer가 여러개 있을때 닫기 작동
                    break;
            }
        }
    };


}