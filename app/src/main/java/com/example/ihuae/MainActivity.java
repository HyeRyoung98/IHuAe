package com.example.ihuae;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.ihuae.R;
import com.example.ihuae.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public MainPagerAdapter adapter;

    private int tab_on_ic_ids[] = {R.drawable.tab_on_ic_home, R.drawable.tab_on_ic_calendar, R.drawable.tab_on_ic_diary, R.drawable.tab_on_ic_chat};
    private int tab_off_ic_ids[] = {R.drawable.tab_off_ic_home, R.drawable.tab_off_ic_calendar, R.drawable.tab_off_ic_diary, R.drawable.tab_off_ic_chat};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        init();
        eventHandler();
    }

    private void init(){
        adapter = new MainPagerAdapter(this.getSupportFragmentManager(), getLifecycle());
        binding.viewPager2.setCurrentItem(0);

        binding.viewPager2.setUserInputEnabled(false);
        binding.viewPager2.setAdapter(adapter);
        binding.viewPager2.setOffscreenPageLimit(4);

        binding.tabLayout.setTabRippleColor(null);
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                if(position == 0) tab.setIcon(tab_on_ic_ids[position]);
                else tab.setIcon(tab_off_ic_ids[position]);
            }
        }).attach();
    }

    private void eventHandler(){

        binding.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(tab_on_ic_ids[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(tab_off_ic_ids[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}