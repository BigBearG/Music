package com.example.shuxing;

import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=(ImageView)findViewById(R.id.iv);
        //属性动画
        ObjectAnimator oa=ObjectAnimator.ofFloat(iv,"translationX",0,10.0f,20.0f,30.0f);
        oa.setDuration(4000);
        oa.setRepeatCount(ObjectAnimator.INFINITE);
        oa.setRepeatMode(ObjectAnimator.REVERSE);
        oa.start();

    }
}
