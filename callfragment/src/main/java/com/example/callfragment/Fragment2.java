package com.example.callfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by liuwei on 7/10/17.
 */
public class Fragment2 extends Fragment {
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragement2,null);
        textView = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }
    public void updateTV(String content){
        textView.setText(content);
    }
}
