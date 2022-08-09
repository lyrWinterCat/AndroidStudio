package parser;

import com.lyr.ex_naverapi.NaverActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.BookVO;

public class Parser {
    //웹에서 요소(제목, 저자, 이미지, 가격)를 검색하여 vo에 저장 및 list에 저장

    BookVO vo;
    String myQuery = ""; //EditText로 받은 검색어

    // 서버에 연결을 해서 xml파일을 불러오고, 자바로 parsing해서 필요한 요소만 vo에 담고 arrayList에 저장해서 list로 반환을 해주는 작업
    public ArrayList<BookVO> connectNaver(){
        ArrayList<BookVO> list = new ArrayList<>();

        //ctrl alt t
        try{
            //검색어 (myQuery)를  UTF-8 형태로 인코딩
            myQuery = URLEncoder.encode(NaverActivity.search.getText().toString(),"UTF-8");

            String urlstr = "https://openapi.naver.com/v1/search/book.xml?query="+myQuery+"&display=100";
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //발급받은 id와 secret을 서버로 전달
            connection.setRequestProperty("X-Naver-Client-Id","F9Xvf6T1ExrtCe2l7Bs0");
            connection.setRequestProperty("X-Naver-Client-Secret","KL3aBROyZQ");
            
            //위의 url을 수행하여 받은 자원들을 자바 코드로 파싱
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            
            //connection 객체가 접속 후 가지게 된 내용을 parser가 스트림으로 읽어옴
            parser.setInput(connection.getInputStream(),null);// null : 인코딩타입
            
            //parser 객체를 통해 각 요소별 접근을 하게 되고, 태그(요소) 내부의  값들을 가져온다.
            // while문을 돌리면서 더 이상 읽어올 데이터(책)이 없을 때까지 모든 정보를 다 가져올 것
            int parserEvent = parser.getEventType();
            while(parserEvent!=XmlPullParser.END_DOCUMENT){ //문서의 끝
                // 서버쪽 xml문서의 끝을 만날 때까지 while문이 반복

                //시작태그의 이름을 가져와 vo에 담을 수 있는 정보라면 vo에 추가
                if(parserEvent ==XmlPullParser.START_TAG){
                    String tagName = parser.getName();
                    if(tagName.equals("title")){
                        vo = new BookVO();
                        String title = parser.nextText();

                        //가져온 title에 <b>태그가 들어있는지 검사
                        //한글자 짜리 태그들을 찾아주면서 닫히는 태그들까지 감지를 해줄 수 있는 정규식
                        Pattern pattern = Pattern.compile("<.*?>");
                        Matcher matcher = pattern.matcher(title);
                        if(matcher.find()){
                            String s_title = matcher.replaceAll("");
                            vo.setB_title(s_title);
                        }else{
                            vo.setB_title(title);
                        }

                        //  next : 다음 줄로 이동 / nextText : 해당 줄의 안쪽으로 이동

                    }else if(tagName.equals("image")){
                        String img = parser.nextText();
                        vo.setB_img(img);
                    }else if(tagName.equals("author")){
                        String author = parser.nextText();
                        vo.setB_author(author);
                    }else if(tagName.equals("price")){
                        String price = parser.nextText();
                        vo.setB_price(price);
                        list.add(vo); //마지막 정보인 price까지 찾고 난 뒤,  list에 저장
                    }
                }
                parserEvent = parser.next(); //다음 요소를 가져올 때 순서대로 가져와야 한다.
            }//while

        }catch(Exception e){

        }


        return list;

    }









}
