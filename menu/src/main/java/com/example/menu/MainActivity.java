package com.example.menu;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        menu.add(0,1,0,"前进");
        menu.add(0,2,0,"后退");
        menu.add(0,3,0,"首页");
        return true;
    }
    //当我们想知道具体点击那些菜单条目，重写下面

    @Override
    public void onBackPressed() {
        Log.d("data","hahah");
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //判断具体选中那个条目
        switch (item.getItemId()){
            case 1:
                Log.d("data","111");
                break;
            case 2:
                Log.d("data","222");
                break;
            case 3:
                Log.d("data","333");
                break;

        }
        return super.onOptionsItemSelected(item);
    }
    //当菜单打开之前调用openMenu

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        //弹出一个对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setPositiveButton("111确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("222取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
        return false;
    }
}
