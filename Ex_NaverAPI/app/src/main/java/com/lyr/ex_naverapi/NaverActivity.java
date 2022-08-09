package com.lyr.ex_naverapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import parser.Parser;
import vo.BookVO;

public class NaverActivity extends AppCompatActivity {

    public static EditText search;

    ListView myListView;
    Button search_btn;
    Parser parser;
    ViewModelAdapter adapter;
    LinearLayout loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver);

        search= findViewById(R.id.search);
        myListView = findViewById(R.id.myListView);
        search_btn = findViewById(R.id.search_btn);
        loading = findViewById(R.id.loading);
        parser = new Parser();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //parser.connectNaver(); >> 해당 행동이 끝날때까지 아무것도 할 수 없기때문에 요즘은 이렇게 쓰지 않음

                //서버에 연결 >> AndroidManifest.xml 파일 설정해주기 + Async클래스의 doInBackground()메서드 호출
                //new NaverAsync().doInBackground();
                new NaverAsync().execute("홍","길","동");

                //로딩 시작
                loading.setVisibility(View.VISIBLE);

            }
        });

        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                switch (i){
                    case EditorInfo.IME_ACTION_SEARCH:
                        //search_btn 버튼을 강제로 클릭
                        search_btn.performClick();
                        break;
                }
                return true;
            }
        });

    }//onCreate()


    /*
    AsyncTask : 백그라운드에서 서버통신을 위해 반드시 필요한 클래스
    AsyncTast  : 3개의 제너릭 타입을 가지고 있음
     - doInBackground의 파라미터 타입
     - onProgressUpdate가 오버라이딩 되어있다면, 이 메서드를 사용할 타입
     - doInBackground의 반환형이자, 작업의 최종 결과를 차지하는 onPostExecute()의 파라미터 타입
     */

    class NaverAsync extends AsyncTask<String, Void, ArrayList<BookVO>>{


        //String... strings
        // : ...의 의미 >> variable arguments 배열의 개수를 따지지 않고 파라미터로 들어오는 
        // 모든 것들을 배열로 만들어주는 기능을 가능하게 한다. *개수의 제한이 없음*
        //Strings[0] ->홍 Strings[1]->길 Strings[2] ->동
        @Override
        protected ArrayList<BookVO> doInBackground(String... strings) {
            //필수 메서드
            // 각종 반복이나 제어 등의 백그라운드에서 필요한 처리 코드를 담당하는 메서드드

            return parser.connectNaver();
        }

        @Override
        protected void onPostExecute(ArrayList<BookVO> bookVOS) {
            //doInBackground에서 return된 최종 작업 결과를 받는 메서드
            //super.onPostExecute(bookVOS);

            //bookVOS를 adapter클래스에게 넘겨주어야 한다(listView를 그리기 위해)
            adapter = new ViewModelAdapter(NaverActivity.this,R.layout.book_item,bookVOS,myListView);

            //준비된 어댑터를  ListView에 탑재
            myListView.setAdapter(adapter);

            //로딩 종료
            loading.setVisibility(View.GONE);

            /*for (int i = 0 ; i<bookVOS.size(); i++){
                Log.i("MY",""+bookVOS.get(i).getB_price());
            }*/
        }
    }

}