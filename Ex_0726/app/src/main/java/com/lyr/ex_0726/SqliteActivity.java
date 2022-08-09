package com.lyr.ex_0726;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.icu.util.Output;
import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class SqliteActivity extends AppCompatActivity {

    SQLiteDatabase mDatabase;
    boolean isFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //assets 폴더의 DB를 휴대폰 내부 장소에 저장
        copyAssets();
    }

    // asserts 폴더의 DB를 휴대폰 내부 장소에 저장하기 위한 메서드
    public void copyAssets(){
        AssetManager assetManager = getAssets();
        String[] files = null;
        String mkdir ="";
        try{



        }catch (Exception e){ //assets폴더의 모든 파일 이름을 가져옴
            //files = assetManager.list("");
            InputStream in = null;
            OutputStream out = null;

            //inputStream으로 읽어오기
            //in = assetManager.open(files[1]);

            //내부 저장소에 폴더 생성
            //휴대폰 내부(기본)저장소의 root(최상위) 경로로 접근,
            String str = ""+ Environment.getExternalStorageDirectory();
            mkdir = str+"/database"; //database라고 하는 이름의 폴더를 생성할 예정

            File mpath = new File(mkdir);
                if(!mpath.exists()){
                    isFirst=true;
                }
                if(isFirst){
                    mpath.mkdirs(); //database 폴더를 실질적으로 생성
                }
            };


    }
}