package com.example.fragement2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取手机分辨率
        WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
        Point point=new Point();
        wm.getDefaultDisplay().getSize(point);
        int height=point.x;
        int weight=point.y;
        //获取Fragment管理者 通过上下文直接获取
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction beginTransaction=fragmentManager.beginTransaction();
        if (height>weight){
            Log.d("data","竖屏");
            //android.R.id.content代表手机当前窗体
            beginTransaction.replace(android.R.id.content,new Fragment1());
        }else {
            beginTransaction.replace(android.R.id.content,new Fragrment2());
        }
        beginTransaction.commit();
    }
}
