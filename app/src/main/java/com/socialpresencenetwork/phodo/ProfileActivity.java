package com.socialpresencenetwork.phodo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ProfileActivity extends FragmentActivity implements View.OnClickListener {

    Context ctx=this;
    Button likes_Bt;
    Button posts_info_Bt;
    Button comments_Bt;
    Button ranks_Bt;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        pager =(ViewPager)findViewById(R.id.pager);
        likes_Bt= (Button)findViewById(R.id.bt_likes);
        likes_Bt.setOnClickListener(this);
        posts_info_Bt= (Button)findViewById(R.id.bt_posts_info);
        posts_info_Bt.setOnClickListener(this);
        comments_Bt= (Button)findViewById(R.id.bt_comments_info);
        comments_Bt.setOnClickListener(this);
        ranks_Bt= (Button)findViewById(R.id.bt_rank_info);
        ranks_Bt.setOnClickListener(this);
        CustomProfilePagerAdapter adapter = new CustomProfilePagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bt_likes:
                pager.setCurrentItem(0);
                break;
            case R.id.bt_posts_info:
                pager.setCurrentItem(1);
                break;
            case R.id.bt_comments_info:
                pager.setCurrentItem(2);
                break;
            case R.id.bt_rank_info:
                pager.setCurrentItem(3);
                break;

        }
    }
}
