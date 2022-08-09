package com.lyr.ex_0715;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuActivity extends AppCompatActivity {

    Button btn_show, anchor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_menu);

        btn_show=findViewById(R.id.btn_show);
        anchor = findViewById(R.id.anchor);

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //팝업메뉴 생성
                PopupMenu popup = new PopupMenu(PopupMenuActivity.this,anchor);

                //팝업 메뉴에 띄워줄 메뉴  xml 파일을 등록
                // inflate() : xml을 view 형태로 바꿔주는 메서드
                // popup.getMenu() : 메뉴가 들어갈 공간을 만들어 주는 메서드
                getMenuInflater().inflate(R.menu.my_menu2, popup.getMenu());


                //메뉴를 클릭해서 원하는 메뉴를 클릭했을 시 나오는 이벤트는
                // 메뉴 버튼이 나오기 전에 처리를 끝내준다.
                // >> 메뉴가 이미 나온 뒤에는 작동을 잘 안 할수도 있으므로
                // 메뉴 버튼이 나오기 전에 만들어주어야 한다.

                popup.setOnMenuItemClickListener( menu_click );

                popup.show();
            }
        });
    }// onCreate()

    //감지자 만들어주기
    PopupMenu.OnMenuItemClickListener menu_click = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            //클릭했을 시 실행할 이벤트 넣어주기
            switch(menuItem.getItemId()){
                case R.id.menu4:
                    Toast.makeText(PopupMenuActivity.this,"메뉴1 클릭",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu5:
                    Toast.makeText(PopupMenuActivity.this,"메뉴2 클릭",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.menu6:
                    Toast.makeText(PopupMenuActivity.this,"메뉴3 클릭",Toast.LENGTH_SHORT).show();
                    break;
            }
            return true;
        }
    };

}