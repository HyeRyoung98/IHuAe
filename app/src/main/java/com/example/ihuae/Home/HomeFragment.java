package com.example.ihuae.Home;

import android.database.Cursor;
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

import com.example.ihuae.Item.GuideCardItem;
import com.example.ihuae.MainActivity;
import com.example.ihuae.R;
import com.example.ihuae.Util.ArrayItems;
import com.example.ihuae.Util.DBContract;
import com.example.ihuae.Util.MainDBHelper;
import com.example.ihuae.databinding.FragmentHomeBinding;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private WriteEmoDialog dialog;
    private GuideCardAdapter adapter;
    private MainDBHelper dbHelper;
    private String emoText = "";
    private int emoIcon = 0;
    private int emoIconID = -1;
    private String iconGuide = "";
    private String todayQuestion = "";
    private ArrayItems arrayItems = new ArrayItems();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        getData();
        setUI();
        eventHandler();
    }

    private void init(){
        dbHelper = new MainDBHelper(getContext());

        adapter = new GuideCardAdapter(getContext());
        binding.guideCardViewPager.setAdapter(adapter);
        binding.guideCardViewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        binding.guideCardViewPager.setOffscreenPageLimit(3);
        binding.guideCardViewPager.setUserInputEnabled(false);
    }

    private void getData(){
        //가이드 카드 가져오기
        String sql = "SELECT * FROM "+ DBContract.GuideCardEntry.TABLE_NAME
                + " WHERE " + DBContract.GuideCardEntry._ID + " <= " + MainActivity.dDay;
        Cursor c = dbHelper.selectData(sql);
        adapter.cardItems.clear();
        while (c.moveToNext()){
            GuideCardItem cardItem = new GuideCardItem();
            cardItem.id = c.getInt(0);
            cardItem.DateID = c.getInt(1);
            cardItem.Content = c.getString(2);
            cardItem.Image = c.getInt(3);
            Log.e("===============[HomeFragment]getData cardItem================", cardItem.toString());
            adapter.cardItems.add(cardItem);
        }
        adapter.notifyDataSetChanged();
        c.close();

        //하루 문답 가져오기
        sql = "SELECT " + DBContract.QnAEntry.COLUMN_NAME_2
                + " FROM " + DBContract.QnAEntry.TABLE_NAME
                + " WHERE " + DBContract.QnAEntry._ID + " = " + MainActivity.dDay;
        c = dbHelper.selectData(sql);
        while (c.moveToNext()){
            todayQuestion = c.getString(0);
        }
        c.close();

        //오늘의 기분 가져오기
        getEmoData();
    }

    private void getEmoData(){
        //오늘의 기분 가져오기
        String sql = "SELECT " + DBContract.EmoEntry.TABLE_NAME + "." + DBContract.EmoEntry.COLUMN_NAME_2
                + " , " + DBContract.IconEntry.TABLE_NAME + "." + DBContract.IconEntry.COLUMN_NAME_1
                + " , " + DBContract.IconEntry.TABLE_NAME + "." + DBContract.IconEntry.COLUMN_NAME_2
                + " , " + DBContract.EmoEntry.TABLE_NAME + "." + DBContract.EmoEntry.COLUMN_NAME_3
                + " FROM " + DBContract.EmoEntry.TABLE_NAME
                + " JOIN " + DBContract.IconEntry.TABLE_NAME
                + " ON " + DBContract.IconEntry.TABLE_NAME + "." + DBContract.IconEntry._ID + " = (" + DBContract.EmoEntry.TABLE_NAME + "." + DBContract.EmoEntry.COLUMN_NAME_2 + " + 1 )"
                + " WHERE 1=1"
                + " AND " + DBContract.EmoEntry.TABLE_NAME + "." + DBContract.EmoEntry._ID + " = " + MainActivity.dDay;
        Cursor c = dbHelper.selectData(sql);
        while (c.moveToNext()){
            emoIconID = c.getInt(0);
            emoIcon = c.getInt(1);
            iconGuide = c.getString(2);
            emoText = c.getString(3);
        }
        c.close();

    }

    private void updateEmoData(){
        //오늘의 기분 가져오기
        String sql = "UPDATE " + DBContract.EmoEntry.TABLE_NAME
                + " SET " + DBContract.EmoEntry.COLUMN_NAME_2 + " = " + emoIconID;
        if(!emoText.isEmpty()) sql += " , " + DBContract.EmoEntry.COLUMN_NAME_3 + " = '" + emoText + "'";
        sql += " WHERE " + DBContract.EmoEntry.TABLE_NAME + "." + DBContract.EmoEntry._ID + " = " + MainActivity.dDay;
        Cursor c = dbHelper.selectData(sql);
        c.close();
    }

    private void setUI(){
        binding.tvDDay.setText("D+"+MainActivity.dDay);
        binding.tvTodayQue.setText(todayQuestion);
        if( emoIconID > -1 ) {
            binding.ivEmo.setImageDrawable(getContext().getDrawable(arrayItems.emoIcons[emoIconID]));
            binding.tvEmo.setText(iconGuide);
        }
        binding.guideCardViewPager.setCurrentItem(adapter.getItemCount()-1);
        setBtnVisible();
        dialog = new WriteEmoDialog(getContext(), emoIcon, emoText);
    }

    private void eventHandler(){
        dialog.setOnEmoDialogListener(new WriteEmoDialog.OnEmoDialogListener() {
            @Override
            public void onResist(int status, String contents) {
                emoIconID = status;
                emoText = contents;
                updateEmoData();
                getEmoData();
                if(status>-1) {
                    binding.ivEmo.setImageDrawable(getContext().getDrawable(arrayItems.emoIcons[status]));
                    binding.tvEmo.setText(iconGuide);
                }
            }
        });
        binding.btnWriteEmo.setOnClickListener(v -> {
            dialog.show();
        });

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

    @Override
    public void onResume() {
        super.onResume();
        getEmoData();
        if(emoIconID>-1) {
            binding.ivEmo.setImageDrawable(getContext().getDrawable(arrayItems.emoIcons[emoIconID]));
            binding.tvEmo.setText(iconGuide);
        }
    }
}
