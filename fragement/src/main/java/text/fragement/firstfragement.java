package text.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liuwei on 7/9/17.
 */

public class firstfragement extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //通过打气筒将布局做成view
        View view=inflater.inflate(R.layout.fragement1,null);
        return view;
    }
}
