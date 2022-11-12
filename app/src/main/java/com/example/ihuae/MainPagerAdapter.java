package com.example.ihuae;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ihuae.Calendar.CalendarFragment;
import com.example.ihuae.Chat.ChatFragment;
import com.example.ihuae.Diary.DiaryFragment;
import com.example.ihuae.Home.HomeFragment;

public class MainPagerAdapter  extends FragmentStateAdapter {

    public MainPagerAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0 :
                fragment = new HomeFragment();
                break;
            case 1 :
                fragment = new CalendarFragment();
                break;
            case 2 :
                fragment = new DiaryFragment();
                break;
            case 3 :
                fragment = new ChatFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}