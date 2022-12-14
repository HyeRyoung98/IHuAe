package com.example.ihuae.Diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ihuae.databinding.FragmentDiaryBinding;

public class DiaryFragment extends Fragment {
    private FragmentDiaryBinding binding;
    private WeeklyAdapter weeklyAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDiaryBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
    }

    private void init(){
        binding.toolbar.tvTile.setText("기록");

        weeklyAdapter = new WeeklyAdapter(getContext());
        binding.dDayRecycler.setAdapter(weeklyAdapter);
        binding.dDayRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
