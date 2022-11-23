package com.example.ihuae.Home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;


import com.example.ihuae.databinding.DialogWriteEmotionBinding;

public class WriteEmoDialog extends Dialog {
    private DialogWriteEmotionBinding binding;
    private EmoIconAdapter adapter;

    private String content = "";
    private int status = -1;

    private Context mContext;

    public OnEmoDialogListener onEmoDialogListener;
    public interface OnEmoDialogListener{
        void onResist(int status, String contents);
    }
    public void setOnEmoDialogListener(OnEmoDialogListener onEmoDialogListener){
        this.onEmoDialogListener = onEmoDialogListener;
    }

    public WriteEmoDialog(@NonNull Context context, int status, String content) {
        super(context);
        this.mContext = context;
        this.status = status;
        this.content = content;
        binding = DialogWriteEmotionBinding.inflate(LayoutInflater.from(context));
        setContentView(binding.getRoot());

        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        this.getWindow().setGravity(Gravity.CENTER); // 가운데 위치하도록

        eventHandler();
    }

    private void eventHandler(){
        binding.btnCancel.setOnClickListener(view -> {
            dismiss();
        });
        adapter = new EmoIconAdapter(mContext);
        adapter.sel_position = status;
        binding.emoRecycler.setLayoutManager(new GridLayoutManager(mContext, 3));
        binding.emoRecycler.setAdapter(adapter);

        binding.etEmo.setText(content);
        adapter.setOnIconClickListener(new EmoIconAdapter.OnIconClickListener() {
            @Override
            public void onClick(int position) {
                status = adapter.sel_position;
                adapter.notifyDataSetChanged();
            }
        });


        binding.btnReg.setOnClickListener(view -> {
            String text = binding.etEmo.getText().toString().trim();
            if(onEmoDialogListener!=null) onEmoDialogListener.onResist(status, text);
            dismiss();
        });
    }
}
