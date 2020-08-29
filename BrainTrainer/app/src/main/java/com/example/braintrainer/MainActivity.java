package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startbutton;
    TextView result;
    TextView sum;
    TextView timer;
    TextView points;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button again;
    ConstraintLayout consplay;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationofans;
    int score=0;
    int numofques=0;
    public void again(View view){
        score=0;
        numofques=0;
        timer.setText("30s");
        points.setText("0/0");
        result.setText("");
        again.setVisibility(View.INVISIBLE);
        genques();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                timer.setText("0s");
                result.setText("Your score"+Integer.toString(score)+"/"+Integer.toString(numofques) );
                again.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void genques(){
        Random rand =new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sum.setText(Integer.toString(a)+ "+"+ Integer.toString(b));
        answers.clear();
        locationofans=rand.nextInt(4);
        int incorrectans;
        for(int i=0;i<4;i++){
            if(i==locationofans){
                answers.add(a+b);
            }else{
                incorrectans=rand.nextInt(41);
                while(incorrectans==a+b){
                    incorrectans=rand.nextInt(41);
                }
                answers.add(incorrectans);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseans(View view){
        if(view.getTag().toString().equals(Integer.toString(locationofans))){
            score++;
            result.setText("CORRECT");
        }else{
            result.setText("WRONG");
        }
        numofques++;
        points.setText(Integer.toString(score)+"/"+Integer.toString(numofques));
        genques();
    }

    public void start(View view){
        startbutton.setVisibility(View.INVISIBLE);
        consplay.setVisibility(ConstraintLayout.VISIBLE);
        again(findViewById(R.id.again));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startbutton=(Button) findViewById(R.id.start);
         button0=(Button)findViewById(R.id.button0);
         button1=(Button)findViewById(R.id.button1);
         button2=(Button)findViewById(R.id.button2);
         button3=(Button)findViewById(R.id.button3);
        result=(TextView) findViewById(R.id.res);
        points=(TextView) findViewById(R.id.points);
         sum=(TextView) findViewById(R.id.sum);
         timer=(TextView) findViewById(R.id.timer);
         again=(Button) findViewById(R.id.again);
       consplay=(ConstraintLayout) findViewById(R.id.conslay);
    }
}