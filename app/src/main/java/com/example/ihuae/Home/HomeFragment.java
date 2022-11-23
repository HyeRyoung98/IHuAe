package com.example.ihuae.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ihuae.R;
import com.example.ihuae.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WriteEmoDialog dialog;
    private GuideCardAdapter adapter;

    private int emoImg[] = {R.drawable.ic_emotion_calmness, R.drawable.ic_emotion_dullness, R.drawable.ic_emotion_sadness
            , R.drawable.ic_emotion_anger, R.drawable.ic_emotion_satisfied, R.drawable.ic_emotion_emptiness};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        eventHandler();
    }

    private void init(){
        dialog = new WriteEmoDialog(getContext(), -1, "");
        dialog.setOnEmoDialogListener(new WriteEmoDialog.OnEmoDialogListener() {
            @Override
            public void onResist(int status, String contents) {
                if(status>-1) binding.ivEmo.setImageDrawable(getContext().getDrawable(emoImg[status]));
                if(!contents.trim().isEmpty()) binding.tvEmo.setText(contents);
                //dayVO.status = status;
                //dayVO.content = contents;
            }
        });
        binding.btnWriteEmo.setOnClickListener(v -> {
            dialog.show();
        });

        adapter = new GuideCardAdapter(getContext());
        binding.guideCardViewPager.setAdapter(adapter);
        binding.guideCardViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.guideCardViewPager.setCurrentItem(adapter.getItemCount()-1);
        binding.guideCardViewPager.setOffscreenPageLimit(3);
        binding.guideCardViewPager.setUserInputEnabled(false);
    }

    private void eventHandler(){
        binding.btnGoPre.setOnClickListener(v -> {
            binding.guideCardViewPager.setCurrentItem(binding.guideCardViewPager.getCurrentItem()-1);
            setBtnVisible();
        });

        binding.btnGoBack.setOnClickListener(v -> {
            binding.guideCardViewPager.setCurrentItem(binding.guideCardViewPager.getCurrentItem()+1);
            setBtnVisible();

        });
    }

    private void setBtnVisible(){
        int curPos = binding.guideCardViewPager.getCurrentItem();
        int preVis = curPos==0? View.INVISIBLE : View.VISIBLE;
        binding.btnGoPre.setVisibility(preVis);
        int backVis = curPos==adapter.getItemCount()-1? View.INVISIBLE : View.VISIBLE;
        binding.btnGoBack.setVisibility(backVis);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}
