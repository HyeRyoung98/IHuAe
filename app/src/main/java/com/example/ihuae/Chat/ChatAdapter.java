package com.example.ihuae.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ihuae.databinding.ItemChatBinding;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.itemVH> {

    public ArrayList<String> chats = new ArrayList<>();

    private Context mContext;
    public ChatAdapter(Context context){
        this.mContext = context;
    }

    @NonNull
    @Override
    public itemVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new itemVH(ItemChatBinding.inflate(LayoutInflater.from(mContext), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.itemVH holder, int position) {
        int topEmptyVis = position == 0? View.VISIBLE : View.GONE;
        holder.binding.topEmpty.setVisibility(topEmptyVis);

        holder.binding.tvContent.setText(chats.get(position));

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class itemVH extends RecyclerView.ViewHolder {
        public ItemChatBinding binding;
        public itemVH(ItemChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
