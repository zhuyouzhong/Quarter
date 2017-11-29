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
import com.example.quarter.adapter.MyFragmentPageAdapter;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/11/26.
 */

public class Groom_Fragment extends Fragment{

    private View view;
    private TabLayout tablayout;
    private ViewPager groom_vp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getContext()).inflate(R.layout.groom_fragment, null);
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
        list.add(new Hot_Fragment());
        list.add(new Follow_Fragment());

        MyFragmentPageAdapter myFragmentPageAdapter=new MyFragmentPageAdapter(getFragmentManager(),list,getContext());
        groom_vp.setAdapter(myFragmentPageAdapter);
       tablayout.setupWithViewPager(groom_vp);
        tablayout.post(new Runnable() {
            @Override
            public void run() {
                Underline_length.setIndicator(tablayout,40,40);
            }
        });
    }

    private void initView() {
        tablayout = view.findViewById(R.id.tablayout);
        groom_vp = view.findViewById(R.id.groom_vp);
    }
}
