package com.example.weixin;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button newsfragment = (Button) findViewById(R.id.news);
        Button contactfragment = (Button) findViewById(R.id.contact);
        Button foundfragment = (Button) findViewById(R.id.found);
        Button mefragment = (Button) findViewById(R.id.me);
        newsfragment.setOnClickListener(this);
        contactfragment.setOnClickListener(this);
        foundfragment.setOnClickListener(this);
        mefragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.news:
                ft.replace(android.R.id.content, new NewsFragment());
                break;
            case R.id.contact:
                ft.replace(android.R.id.content, new ContactFragment());
                break;
            case R.id.found:
                ft.replace(android.R.id.content, new FoundFragment());
                break;
            case R.id.me:
                ft.replace(android.R.id.content, new MeFragment());
                break;
        }
        ft.commit();
    }
}
