package com.lyr.ex_naverapi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vo.BookVO;

public class ViewModelAdapter extends ArrayAdapter<BookVO> {
    Context context;
    int resource;
    BookVO vo;
    ArrayList<BookVO> list;


    public ViewModelAdapter(Context context, int resource, ArrayList<BookVO> list, ListView myListView) {
        super(context, resource,list);

        this.context = context;
        this.resource = resource;
        this.list = list;

        myListView.setOnItemClickListener(click);
    }//생성자

    //listView의 클릭을 감지하는 감지자 생성
    AdapterView.OnItemClickListener click = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String title = list.get(i).getB_title();
            String author = list.get(i).getB_author();
            String price = list.get(i).getB_price();
            String img = list.get(i).getB_img();

            //화면 전환을 위한 intent 준비
            Intent intent = new Intent(context,SubActivity.class);
            intent.putExtra("title",title);
            intent.putExtra("author",author);
            intent.putExtra("price",price);

            intent.putExtra("img",img); //putExtra에 이미지뷰를 담을 수 없기 때문에 경로만 보냄

            context.startActivity(intent);

        }
    };


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        myListView.setAdapter(adapter) 하는 순간 호출되는 메서드 (getView())
        생성자에 파라미터를 받은 사이즈 만큼 getView()메서드가 반복 호출
         */

        //xml파일을 view 형태로 만들 준비
        LayoutInflater linf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        //convertView >> book_item.xml 짝꿍이 없는 xml파일을 view 형태로 변환
        convertView = linf.inflate(resource,null);

        vo = list.get(position); //position이 0~99번까지 알아서 반복하기 때문에 반복문을 쓰지 않아도
        //100개에 대한 내용을 vo에 순차적으로 담을 수 있다

        TextView title = convertView.findViewById(R.id.book_title); //findview사용이 안되기때문에 convertView로 접근
        TextView author = convertView.findViewById(R.id.book_author);
        TextView price = convertView.findViewById(R.id.book_price);
        ImageView img = convertView.findViewById(R.id.book_img);

        title.setText(vo.getB_title());
        author.setText(vo.getB_author());
        price.setText(vo.getB_price()+"원");

        new ImgAcync(img,vo).execute(); //doInBackground() 호출

        //return super.getView(position, convertView, parent);
        return convertView; // list 한 칸에 들어갈 내용
    }

    //이미지를 가져올 Async 클래스
    class ImgAcync extends AsyncTask<Void,Void, Bitmap>{
        Bitmap bm;
        ImageView img;
        BookVO vo;

        public ImgAcync(ImageView img, BookVO vo){
            this.img = img;
            this.vo = vo;
        }


        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                //vo가 가지고 있는 vo.getB_img()를 통해 이미지 경로를 따라 들어가기
                URL img_url = new URL(vo.getB_img());

                // BufferedInputStream을 통해 이미지를 로드 : 전용공간을 만들어서 데이터를 빠르게 가져울 수 있다.
                // buffered : InputStream을 도와 더 빨리 데이터를 입력, 출력하기 위한 Stream

                BufferedInputStream bis = new BufferedInputStream(img_url.openStream());

                //bis가 읽어온 데이터를 이미지로 변환하기 위해 bitmap 형태로 변경
                bm = BitmapFactory.decodeStream(bis);
                bis.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            if(bm!=null){
                return bm;
            }
            //불러올 이미지가 없을 경우 기본 이미지로 bitmap 설정
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.rabbit);

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //super.onPostExecute(bitmap);
            //비트맵 객체를 이미지뷰로 전환
            img.setImageBitmap(bitmap);


        }
    }


}
