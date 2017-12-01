package com.example.quarter.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quarter.R;
import com.example.quarter.Underline_length;
import com.example.quarter.adapter.MyVideoFragmentPageAdapter;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Video_Fragment extends Fragment{

    private View view;
    private TabLayout video_tablayout;
    private ViewPager video_groom_vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.video_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<Fragment> list=new ArrayList<>();
        list.add(new Hot_Video_Fragment());
        list.add(new Fujin_Fragment());

        MyVideoFragmentPageAdapter myFragmentPageAdapter=new MyVideoFragmentPageAdapter(getFragmentManager(),list,getContext());
        video_groom_vp.setAdapter(myFragmentPageAdapter);
        video_tablayout.setupWithViewPager(video_groom_vp);
        video_tablayout.post(new Runnable() {
            @Override
            public void run() {
                Underline_length.setIndicator(video_tablayout,40,40);
            }
        });
    }

    private void initView() {
        video_tablayout = view.findViewById(R.id.video_tablayout);
        video_groom_vp = view.findViewById(R.id.video_groom_vp);
    }
}
