package com.example.ihuae.Home;

import static com.example.ihuae.databinding.ItemGuideCardBinding.inflate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae.Item.GuideCardItem;
import com.example.ihuae.databinding.ItemGuideCardBinding;

import java.util.ArrayList;

public class GuideCardAdapter extends RecyclerView.Adapter<GuideCardAdapter.itemVH> {
    private Context mContext;
    public ArrayList<GuideCardItem> cardItems = new ArrayList<>();
    public GuideCardAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(ItemGuideCardBinding.inflate(LayoutInflater.from(mContext), parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        holder.binding.ivGuideCard.setImageResource(cardItems.get(position).Image);

    }

    @Override
    public int getItemCount() {
        return cardItems.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public ItemGuideCardBinding binding;
        public itemVH(ItemGuideCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
