package com.example.callfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm=getFragmentManager();
        FragmentTransaction bfm=fm.beginTransaction();
        bfm.replace(R.id.ll1,new Fragment1(),"f1");
        bfm.replace(R.id.ll2,new Fragment2(),"f2");
        bfm.commit();
    }
}
