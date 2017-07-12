package text.baidu;

/**
 * Created by liuwei on 7/9/17.
 */

public interface myMusicService {
    public void startPlay(String name);
    public void pausePlay();
    public void stopPlay();
    public void repaly();
    public void seekToPlay(int position);
}
