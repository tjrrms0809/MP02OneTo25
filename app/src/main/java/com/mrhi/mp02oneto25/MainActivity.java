package com.mrhi.mp02oneto25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btnRetry;

    Button[] btns= new Button[25];//Button참조변수가 25개짜리인 배열객체

    //현재 눌러야 할 번호
    int number=1;

    //버튼의 배경그림객체 참조변수
    Drawable backDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv= findViewById(R.id.tv);
        btnRetry= findViewById(R.id.btn_retry);

        Random rnd= new Random();
        //중복되지 않는 랜덤값 25개를 가진 배열
        int[] arr= new int[25];
        for(int i=0; i<25; i++){
            arr[i]= rnd.nextInt(25)+1; //1~25
            for(int k=0; k<i; k++){
                if(arr[i]== arr[k]){
                    i--;
                    break;
                }
            }
        }

        //Button참조변수들에게 Button객체들 대입하기!
        for(int i=0; i<25; i++){
            btns[i]= findViewById(R.id.btn01 + i);
            btns[i].setText( arr[i] +"" );
            btns[i].setTag(arr[i]);
        }

        //버튼의 배경이미지 얻어오기
        backDrawable= btns[0].getBackground();

    }//onCreate Method..

    //onClick속성에 지정한 콜백메소드
    //onClick속성으로 지정한 메소드가 자동 실행됨
    public void clickBtn(View v){
        //매개변수로 전달된 View v가 현재 클릭한 뷰(버튼)
        //클릭된 버튼의 글씨를 얻어오기
        Button btn= (Button)v;//다운캐스팅
        //String s= btn.getText().toString();

        //버튼에 기록된 Tag값을 얻어오기
        String s= btn.getTag().toString();

        //얻어온 글씨를 int형으로 변환!
        int n= Integer.parseInt(s);

        //얻어온 글씨와 현재 누를 번호(number)와 같은지 비교
        if( n == number ){
            //눌러야 할 번호를 잘 눌렀다!!
            btn.setText("OK");//버튼의 글씨변경
            btn.setTextColor(0xFFFF0000); //ARGB
            btn.setBackgroundColor(Color.TRANSPARENT);

            //버튼을 안보이도록 하려면..
            //btn.setVisibility(View.INVISIBLE);

            //눌러야 번호를 증가
            number++;

            if(number>25){
                tv.setText("CLEAR!!!");
                btnRetry.setEnabled(true);
            }else{
                tv.setText(number+"");
            }
        }

    }


    //retry버튼에 지정된 onclick속성 콜백메소드
    public void clickRetry(View v){

        Random rnd= new Random();

        int[] arr= new int[25];
        for(int i=0; i<arr.length; i++){
            arr[i]= rnd.nextInt(25)+1;

            //중복검사 작업///////
            for(int k=0; k<i; k++){
                if(arr[i]==arr[k]){
                    i--;
                    break;
                }
            }
            //////////////////////
        }

        for(int i=0; i<btns.length; i++){
            btns[i].setText(arr[i]+"");
            btns[i].setTag(arr[i]);
            btns[i].setTextColor(Color.BLACK);
            btns[i].setBackground(backDrawable);

        }

        number=1;
        tv.setText(number+"");

        btnRetry.setEnabled(false);
    }



}//MainActivity class..
