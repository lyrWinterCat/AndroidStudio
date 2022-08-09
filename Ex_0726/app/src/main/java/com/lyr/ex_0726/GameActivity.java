package com.lyr.ex_0726;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    ImageView computer_img, user_img;
    Button btn_gawi, btn_rock, btn_bo, btn_start, btn_exit;
    TextView game_result;

    int[] computer_imgs = new int[]{R.drawable.cs,R.drawable.cr,R.drawable.cp};
    int[] user_imgs = new int[] {R.drawable.us, R.drawable.ur, R.drawable.up};
    // 가위 바위 보

    int computer_img_id = (int)(Math.random()*computer_imgs.length);
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        computer_img= findViewById(R.id.computer_img);
        user_img= findViewById(R.id.user_img);

        btn_gawi=findViewById(R.id.btn_gawi);
        btn_rock=findViewById(R.id.btn_rock);
        btn_bo=findViewById(R.id.btn_bo);
        btn_start=findViewById(R.id.btn_start);
        btn_exit=findViewById(R.id.btn_exit);
        game_result = findViewById(R.id.game_result);

        btn_gawi.setOnClickListener(click);
        btn_rock.setOnClickListener(click);
        btn_bo.setOnClickListener(click);
        btn_start.setOnClickListener(click);
        btn_exit.setOnClickListener(click);

        btn_gawi.setEnabled(false);
        btn_rock.setEnabled(false);
        btn_bo.setEnabled(false);

    }

    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            computer_img_id = (int)(Math.random()*computer_imgs.length);
            switch(view.getId()){
                case R.id.btn_gawi:
                    computer_img.setImageResource(computer_imgs[computer_img_id]);
                    user_img.setImageResource(user_imgs[0]);
                    handler.removeMessages(0);

                    if(computer_img_id==0){
                        game_result.setText("비겼습니당~~");
                    }else if(computer_img_id==1){
                        game_result.setText("졌습니당...");
                    }else{
                        game_result.setText("이겼습니당!!!");
                    }
                    btn_gawi.setEnabled(false);
                    btn_rock.setEnabled(false);
                    btn_bo.setEnabled(false);
                    break;

                case R.id.btn_rock:
                    computer_img.setImageResource(computer_imgs[computer_img_id]);
                    user_img.setImageResource(user_imgs[1]);
                    handler.removeMessages(0);

                    if(computer_img_id==1){
                        game_result.setText("비겼습니당~~");
                    }else if(computer_img_id==2){
                        game_result.setText("졌습니당...");
                    }else{
                        game_result.setText("이겼습니당!!!");
                    }
                    btn_gawi.setEnabled(false);
                    btn_rock.setEnabled(false);
                    btn_bo.setEnabled(false);
                    break;
                case R.id.btn_bo:
                    computer_img.setImageResource(computer_imgs[computer_img_id]);
                    user_img.setImageResource(user_imgs[2]);
                    handler.removeMessages(0);

                    if(computer_img_id==2){
                        game_result.setText("비겼습니당~~");
                    }else if(computer_img_id==0){
                        game_result.setText("졌습니당...");
                    }else{
                        game_result.setText("이겼습니당!!!");
                    }

                    btn_gawi.setEnabled(false);
                    btn_rock.setEnabled(false);
                    btn_bo.setEnabled(false);
                    break;

                case R.id.btn_start:
                    handler.sendEmptyMessage(0);
                    game_result.setText("결과는??");
                    btn_gawi.setEnabled(true);
                    btn_rock.setEnabled(true);
                    btn_bo.setEnabled(true);

                    break;
                case R.id.btn_exit:
                    handler.removeMessages(0);
                    finish();
                    break;

            }
        }
    };

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //백그라운드에서 코드를 실행하는 영역
            //computer_img_id = (int)(Math.random()*computer_imgs.length);
            moving();

            handler.sendEmptyMessageDelayed(0,150);
            computer_img.setImageResource(computer_imgs[count]);
        }
    };

    public void moving(){
        count--;
        if(count==-1){
            count=2;
        }
    }


}