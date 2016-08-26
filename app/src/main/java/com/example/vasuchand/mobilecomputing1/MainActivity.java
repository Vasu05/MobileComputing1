package com.example.vasuchand.mobilecomputing1;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
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
    private TextView hint , hintmsg,cheat,cheatmsg;
    private Context context;
    int maximum = 1000,minimum = 0;
    int flag1=0,flag2 =0,flag3 =0;
    int c =0,w=0;

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
        hint  = (TextView)findViewById(R.id.h1);
        hintmsg  = (TextView)findViewById(R.id.h2);
        cheat  = (TextView)findViewById(R.id.c1);
        cheatmsg  = (TextView)findViewById(R.id.c2);

        context = this;
        randomness();


        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkforprime();
                c=1;
                correct.setEnabled(false);
                wrong.setEnabled(false);
                if (flag1 == 1)
                {
                    result.setText("Right");
                    Toast.makeText(MainActivity.this,
                            "right", Toast.LENGTH_LONG).show();
                }
                else
                {
                    result.setText("Wrong");
                    Toast.makeText(MainActivity.this,
                            "Wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
        wrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkforprime();
                c=1;
                wrong.setEnabled(false);
                correct.setEnabled(false);
                if (flag1 == 1) {
                    result.setText("Wrong");
                    Toast.makeText(MainActivity.this,
                            "Wrong", Toast.LENGTH_LONG).show();
                } else {
                    result.setText("Right");
                    Toast.makeText(MainActivity.this,
                            "Right", Toast.LENGTH_LONG).show();
                }
            }
        });
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checkforprime();

                hint.setTextColor(ContextCompat.getColor(context, R.color.tru));
                hintmsg.setText("you have taken hint");
                flag2=1;
                hintmsg.setTextColor(ContextCompat.getColor(context, R.color.some_color));
                Intent i = new Intent(getApplicationContext(),
                        hint.class);
                startActivity(i);


            }
        });
        cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 checkforprime();
                flag3=1;
                cheat.setTextColor(ContextCompat.getColor(context, R.color.tru));
                cheatmsg.setText("you have taken cheat");
                cheatmsg.setTextColor(ContextCompat.getColor(context, R.color.some_color));
                String say;
                if(flag1==1)
                {
                    say = "yes";
                }
                else
                    say = "no";
                Intent i = new Intent(getApplicationContext(),
                        cheat.class);

                String cc = screen.getText().toString();


                i.putExtra("category", cc);
                i.putExtra("prime",say);
                startActivity(i);

            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checkforprime();
                randomness();
                correct.setEnabled(true);
                wrong.setEnabled(true);
                c=0;
                w=0;
                flag2=flag3=0;
                result.setText(" ");
                hintmsg.setText("");
                cheatmsg.setText("");
                hint.setTextColor(ContextCompat.getColor(context, R.color.defaults));
                cheat.setTextColor(ContextCompat.getColor(context, R.color.defaults));

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        String  number = screen.getText().toString();
        String status = result.getText().toString();
        String trick = hintmsg.getText().toString();
        String trick2 = cheatmsg.getText().toString();

        int a = c;
        int b = w;
        // ;
        savedInstanceState.putString("text", number);
        savedInstanceState.putString("stat",status);
        savedInstanceState.putString("hint",trick);
        savedInstanceState.putString("cheat", trick2);
        savedInstanceState.putInt("ae",a);
        savedInstanceState.putInt("be",b);

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
        String sq = savedInstanceState.getString("hint");
        String sq2 = savedInstanceState.getString("cheat");
        int  a = savedInstanceState.getInt("ae");
        int b = savedInstanceState.getInt("be");
        if(a==1)
        {
            correct.setEnabled(false);
            wrong.setEnabled(false);
        }


        System.out.println(sq + " "+ sq2);
        if(!sq.isEmpty())
        {
            hint.setTextColor(ContextCompat.getColor(context, R.color.tru));
            hintmsg.setText("you have taken hint");
            hintmsg.setTextColor(ContextCompat.getColor(context, R.color.some_color));
        }
        if(!sq2.isEmpty())
        {
            cheat.setTextColor(ContextCompat.getColor(context, R.color.tru));
            cheatmsg.setText("you have taken cheat");
            cheatmsg.setTextColor(ContextCompat.getColor(context, R.color.some_color));
        }
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
