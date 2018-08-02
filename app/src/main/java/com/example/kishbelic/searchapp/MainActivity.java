package com.example.kishbelic.searchapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgV;
    ProgressBar pBar;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imgV = (ImageView) findViewById(R.id.imgV);
        pBar = (ProgressBar)findViewById(R.id.pBar);
        btn = (Button)findViewById(R.id.btn);




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //btn.setVisibility(View.INVISIBLE);
                btn.animate().alpha(0.0f).setDuration(300);
                pBar.setVisibility(View.VISIBLE);

                pBar.animate().start();


                new CountDownTimer(3000,900) {
                    @Override
                    public void onTick(long l) {

                        Log.i("logman",""+l);

                    }

                    @Override
                    public void onFinish() {

                        pBar.animate().cancel();
                        ToSecondActivity();
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

                        pBar.setVisibility(View.INVISIBLE);
                        btn.animate().alpha(1.0f).setDuration(1000);

                    }
                }.start();


            }
        });



    }


public void ToSecondActivity(){

    Intent intent = new Intent(getApplicationContext(),SearchActivity.class);

    startActivity(intent);
}





}
