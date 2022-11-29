package com.example.ihuae.Diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ihuae.databinding.ItemWeeklyBinding;

import java.util.ArrayList;

public class WeeklyAdapter extends RecyclerView.Adapter<WeeklyAdapter.itemVH> {

    private Context mContext;
    public WeeklyAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(ItemWeeklyBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        holder.binding.tvDDay.setText(position+1+"");
        //todo 아이콘 적용, 투명도 조절 적용, 글씨체 변경 적용, 디데이 불러오기 필요
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public ItemWeeklyBinding binding;
        public itemVH(ItemWeeklyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
