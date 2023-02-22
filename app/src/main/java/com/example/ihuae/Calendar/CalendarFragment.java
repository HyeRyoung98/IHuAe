package com.example.ihuae.Calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.ihuae.databinding.FragmentCalendarBinding;
import com.example.ihuae.databinding.ItemWeekBinding;

public class CalendarFragment extends Fragment {

    private FragmentCalendarBinding binding;
    private ItemWeekBinding weekBinding;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCalendarBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init(){
        binding.tvTile.setText("캘린더");
        setCalWeek();
    }

    private void setCalWeek(){
        String[] weekNM = {"일", "월", "화", "수", "목", "금", "토"};
        for(int i = 0; i < 7 ; i++){
            //TextView tv =  (TextView) binding.calWeekContainer.getChildAt(i);
            //tv.setText(weekNM[i]);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
