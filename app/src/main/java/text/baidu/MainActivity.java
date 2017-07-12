package text.baidu;
import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import static android.content.Context.BIND_AUTO_CREATE;

public class MainActivity extends AppCompatActivity {
    private  myMusicService iMusicService;
    private MyConn conn;
    private EditText et_name;
    private static SeekBar seekBar;
    public static Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            //获取数据
            Bundle bundle=msg.getData();
            int duration=bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");
            seekBar.setMax(duration);
            seekBar.setProgress(currentPosition);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
        Intent intent=new Intent(this,MusicService.class);
        startService(intent);
        conn=new MyConn();
        bindService(intent,conn,BIND_AUTO_CREATE);

        //找到seekBar
        seekBar=(SeekBar)findViewById(R.id.sb);
        //设置监听事件
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //当进度改变调用
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //拖动停止调用
                iMusicService.seekToPlay(seekBar.getProgress());
            }
        });
        et_name=(EditText)findViewById(R.id.name);
    }
    public void play(View v){
        String name=et_name.getText().toString().trim();
        iMusicService.startPlay(name);
    }
    public void replay(View v){
        iMusicService.repaly();
    }
    public void pause(View v){
        iMusicService.pausePlay();
    }
    public void stop(View v){
        iMusicService.stopPlay();
    }
    private class MyConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMusicService=(myMusicService)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length<0&&grantResults[0]!=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"不授权将无法使用",Toast.LENGTH_LONG).show();
                }break;
        }
    }
}
