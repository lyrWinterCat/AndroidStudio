package com.lyr.ex_0727;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteActivity extends AppCompatActivity {

    //안드로이드에서 기본적으로 제공을 해주는 클래스
    SQLiteDatabase mDatabase;
    SharedPreferences pref;

    TextView result_txt;
    Button btn_all, btn_search, btn_insert, btn_delete;
    EditText edit_name, edit_phone, edit_age;


    //처음 한 번 복사를 하면 다음번에 킬 때 또 복사를 할 일이 없도록 만들어줄 변수
    boolean isFirst=true;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        pref= PreferenceManager.getDefaultSharedPreferences(SqliteActivity.this);


        load();
        //애플리케이션이 최초에 실행되었을 때 assets 폴더의 DB를 휴대폰 내부에 저장
        copyAssets();
        save();

        //위에서 copyAssets()을 통해서 복사된 DB를 읽기(mDatabase를 읽어와야함)
        mDatabase = openOrCreateDatabase(Environment.getExternalStorageDirectory()+"/database/myDB.db",SQLiteDatabase.CREATE_IF_NECESSARY,null);

        edit_name = findViewById(R.id.edit_name);
        edit_phone = findViewById(R.id.edit_phone);
        edit_age = findViewById(R.id.edit_age);

        btn_all = findViewById(R.id.btn_all);
        btn_search = findViewById(R.id.btn_search);
        btn_insert = findViewById(R.id.btn_insert);
        btn_delete = findViewById(R.id.btn_delete);

        result_txt=findViewById(R.id.result_txt);

        btn_all.setOnClickListener(myClick);
        btn_search.setOnClickListener(myClick);
        btn_insert.setOnClickListener(myClick);
        btn_delete.setOnClickListener(myClick);

    }

    View.OnClickListener myClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_all: //전체조회
                    searchQuery("select * from friend");
                    break;
                case R.id.btn_search: //상세조회
                    String name = edit_name.getText().toString().trim();
                    if(name.length()==0){
                        Toast.makeText(SqliteActivity.this, "검색할 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }else{
                     searchQuery(String.format("select * from friend where name='%s'",name));
                    }
                    break;
                case R.id.btn_insert: //정보추가
                    String insert_name= edit_name.getText().toString().trim();
                    String insert_phone= edit_phone.getText().toString().trim();
                    String insert_age = edit_age.getText().toString().trim();

                    if(!(insert_name.length()==0 && insert_phone.length()==0 && insert_age.length()==0)) {
                        int age = Integer.parseInt(insert_age);
                        searchQuery(String.format("insert into friend values('%s','%s',%d)",insert_name,insert_phone,age));
                        edit_name.setText("");
                        edit_phone.setText("");
                        edit_age.setText("");
                        //데이터 추가 후 전체 로드
                        searchQuery("select * from friend");
                    }else{
                        Toast.makeText(SqliteActivity.this, "추가할 내용들을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }


                    break;
                case R.id.btn_delete: //정보 삭제
                    String delete_name = edit_name.getText().toString().trim();
                    if(delete_name.length()==0){
                        Toast.makeText(SqliteActivity.this, "삭제할 회원의 이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    }else{
                        searchQuery(String.format("delete from friend where name='%s'",delete_name));
                        searchQuery("select * from friend");
                        edit_name.setText("");
                        edit_phone.setText("");
                        edit_age.setText("");
                    }
                        searchQuery("select * from friend");

                    break;
            }
        }
    };

    //쿼리문을 수행할 메서드
    public void searchQuery(String query){ // 내가 요청하고 싶은 쿼리문을 파라미터로 정한다
        Cursor c = mDatabase.rawQuery(query,null);

        //컬럼의 수 만큼 배열의 공간을 확보해라라
       //String[] column = new String[c.getColumnCount()];
       String[] column = c.getColumnNames();

       String[] str = new String[c.getColumnCount()];
       String result = ""; //최종 결과물을 저장해 둘 변수
       //Log.i("MY",column[0]+"/"+column[1]+"/"+column[2]);

        //행 단위로 한 줄씩 커서가 이동
        while(c.moveToNext()){
            for(int i=0; i<column.length; i++){
                str[i] = "";
                str[i]+=c.getString(i);

                result += column[i] + " : "+str[i]+"\n";
            }
            result+="\n";
        }
            result_txt.setText(result);

    }


   //assets폴더의 DB를 휴대폰 내부에 저장하기 위판 메서드 작성
    private void copyAssets() {
        // inputStream으로 읽어서 outputStream으로 휴대폰 내부에 저장을 할 것
        AssetManager assetManager = getAssets();

        String [] files = null;
        String mkdir = "";

        try {

            files = assetManager.list("");
            InputStream in = null;
            OutputStream out = null;

            //files[0]의 값인 "myDB.db"와 같은 이름의 파일을 찾아서
            // inputStream으로 읽어온다.
            in = assetManager.open(files[1]);

            //내부저장소에 폴더 생성
            //휴대폰 내부(기본) 저장소의 root (최상위) 경로로 접근
            String str = ""+ Environment.getExternalStorageDirectory(); //경로까지 접근
            mkdir = str + "/database"; // database라는 이름의 폴더를 생성할 예정

            File mpath = new File(mkdir);

            if(!mpath.exists()){
                isFirst=true;
            }
            if(isFirst){ //isFirst가 참 >> database라는 폴더를 아직 못찾았다는 이야기
                mpath.mkdirs(); //database라는 폴더를 만들어라
                //database이름의 폴더까지 접근해서 myDB.db라는 이름으로 output할 준비를 해주어야 함
                //root/database/myDB.db
                out = new FileOutputStream(mkdir + "/"+files[1]);

                byte[] buffer = new byte[2048];
                int read = 0;

                while((read = in.read(buffer))!= -1){ //문서의 끝을 만날 때까지
                    out.write(buffer,0,read);
                }

                //stream을 역순으로 닫아주기
                out.close();
                in.close();

                isFirst=false;

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void save(){
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("save",isFirst);
    }

    public void load(){
        //save라는 key값으로 저장되어 있는 값을 불러오고,
        // 만약 값이 저장되어 있지 않다면 기본값은 true
        isFirst=pref.getBoolean("save",true);
    }




}