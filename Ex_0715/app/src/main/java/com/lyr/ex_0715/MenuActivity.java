package com.lyr.ex_0715;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 사용을 위한 메뉴 리소스 파일 (my_menu.xml)을 등록
        // xml 참조파일을 눈으로 확인하기 위해 inflater가 변환을 해준다.
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { // item : 어떤 메뉴가 눌러졌는지 알고 있는 객체
        switch (item.getItemId()){
            case R.id.menu1:
                //Toast.makeText(context, 출력하고싶은 내용, 몇 초 동안 보여줄건지).show();
                Toast.makeText(MenuActivity.this,"앱소개클릭",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu2:
                Toast.makeText(this.getApplicationContext(),"이메일클릭",Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu3:
                Toast.makeText(this.getApplicationContext(),"종료클릭",Toast.LENGTH_SHORT).show();
                //현재 띄워져 있는 액티비티 한 개 종료
                finish();
                break;
        }


        return true;
    }
}