package com.example.ihuae.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ihuae.R;
import com.example.ihuae.databinding.ItemEmotionIconBinding;

public class EmoIconAdapter extends RecyclerView.Adapter<EmoIconAdapter.itemVH> {
    private Context mContext;
    public OnIconClickListener onIconClickListener;
    public int sel_position = 0;
    public interface OnIconClickListener{
        void onClick(int position);
    }
    public void setOnIconClickListener(OnIconClickListener onIconClickListener){
        this.onIconClickListener = onIconClickListener;
    }

    private int emoImg[] = {R.drawable.ic_emotion_calmness, R.drawable.ic_emotion_dullness, R.drawable.ic_emotion_sadness
            , R.drawable.ic_emotion_anger, R.drawable.ic_emotion_satisfied, R.drawable.ic_emotion_emptiness};
    private String emoTxt[] = {"평온", "무덤덤", "슬픔", "분노", "만족", "공허함" };

    public EmoIconAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(ItemEmotionIconBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull itemVH holder, int position) {
        holder.binding.ivEmo.setImageDrawable(mContext.getDrawable(emoImg[position]));
        holder.binding.tvEmo.setText(emoTxt[position]);
        if(sel_position-1 != position){
            holder.binding.btnBgEmo.setChecked(false);
        }
        holder.binding.btnBgEmo.setOnClickListener(view -> {
            if(onIconClickListener != null) onIconClickListener.onClick(holder.getLayoutPosition());
            sel_position = holder.getLayoutPosition()+1;
        });
    }

    @Override
    public int getItemCount() {
        return emoImg.length;
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public ItemEmotionIconBinding binding;
        public itemVH(ItemEmotionIconBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
