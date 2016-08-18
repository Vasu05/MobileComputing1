package com.example.vasuchand.mobilecomputing1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView screen,correct,wrong,skip,result;
    int maximum = 1000,minimum = 0;
    int flag1=0,flag2 =0,flag3 =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.t1);
        correct = (TextView)findViewById(R.id.t2);
        wrong = (TextView)findViewById(R.id.t3);
        skip =(TextView)findViewById(R.id.t4);
        result = (TextView)findViewById(R.id.result);


        randomness();


        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkforprime();
                if (flag1 == 1) {
                    result.setText("Correct");
                     Toast.makeText(MainActivity.this,
                            "correct", Toast.LENGTH_LONG).show();
                } else {
                    result.setText("Not Correct");
                    Toast.makeText(MainActivity.this,
                            "not correct", Toast.LENGTH_LONG).show();
                }
            }
        });
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkforprime();
                if (flag1 == 1) {
                    result.setText("Not Correct");
                    Toast.makeText(MainActivity.this,
                            "not correct", Toast.LENGTH_LONG).show();
                } else {
                    result.setText("Correct");
                     Toast.makeText(MainActivity.this,
                            "correct", Toast.LENGTH_LONG).show();
                }
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checkforprime();
                randomness();
                result.setText(" ");

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        String  number = screen.getText().toString();
        String status = result.getText().toString();
        // ;
        savedInstanceState.putString("text", number);
        savedInstanceState.putString("stat",status);
        //

        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate
        String myString = savedInstanceState.getString("text");
        String st = savedInstanceState.getString("stat");
        screen.setText(myString);
        result.setText(st);
    }

    public void randomness(){
        Random rn = new Random();
        int range = maximum - minimum + 1;
        int randomNum =  rn.nextInt(range) + minimum;
        String c = String.valueOf(randomNum);
        screen.setText(c);
    }
    public  void checkforprime(){

        String cc = screen.getText().toString();
        int num = Integer.valueOf(cc);
        int flag=0;
        for(int i=2;i<num;i++){
            if(num%i==0)
            {
                System.out.println(num+" is not a Prime Number");
                flag = 1;
                flag1=0;
                break;
            }
        }
        if(flag==0){
            System.out.println(num+" is a Prime Number");
            flag1 =1;
        }
    }
}
