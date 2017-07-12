package text.baidu;

/**
 * Created by liuwei on 7/9/17.
 */

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by liuwei on 7/8/17.
 */

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;
    private class myBinder extends Binder implements myMusicService{
        @Override
        public void startPlay(String name) {
            playMusic(name);
        }

        @Override
        public void pausePlay() {
            pauseMusic();
        }

        @Override
        public void stopPlay() {
            stopMusic();
        }
        @Override
        public void repaly() {
            replayMusic();
        }

        @Override
        public void seekToPlay(int position) {
            seekTo(position);
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new myBinder() ;
    }
    public void playMusic(String name){
        System.out.println("音乐播放");
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource("http://10.39.1.16/"+name+".mp3");
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    System.out.println("我准备好了");
                    mediaPlayer.start();
                }
            });
            updateSeekBar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSeekBar() {
        //获取当前播放总进度
        final int duration=mediaPlayer.getDuration();
        //使用timer
        final Timer timer=new Timer();
        final TimerTask task=new TimerTask() {
            @Override
            public void run() {
                int currentPosition=mediaPlayer.getCurrentPosition();
                Message msg=Message.obtain();
                Bundle bundle=new Bundle();
                bundle.putInt("duration",duration);
                bundle.putInt("currentPosition",currentPosition);
                msg.setData(bundle);
                MainActivity.handler.sendMessage(msg);
            }
        };
        timer.schedule(task,100,1000);
        //当歌曲执行完毕后取消
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                timer.cancel();
                task.cancel();
            }
        });
    }

    public void replayMusic(){
        mediaPlayer.start();
    }
    public void pauseMusic(){
        System.out.println("音乐暂停");
        mediaPlayer.pause();
    }
    public void stopMusic(){
        System.out.println("音乐停止");
        mediaPlayer.stop();
        onDestroy();
        onCreate();
    }
    public void seekTo(int position){
        mediaPlayer.seekTo(position);
    }
    @Override
    public void onCreate() {
        System.out.println("音乐服务创建");
        mediaPlayer = new MediaPlayer();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        System.out.println("音乐服务销毁");
        super.onDestroy();
    }
}
