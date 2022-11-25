package com.example.ihuae.Chat;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ihuae.Util.DpToPxConverter;
import com.example.ihuae.databinding.FragmentChatBinding;

import java.util.Calendar;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter adapter;
    private DpToPxConverter dpToPxConverter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(LayoutInflater.from(getContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated( View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        eventHandler();
    }

    private void init(){
        binding.toolbar.tvTile.setText("감정 억제기");

        adapter = new ChatAdapter(getContext());
        binding.chatRecycler.setAdapter(adapter);
        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        createTestData();       //test
        binding.chatRecycler.scrollToPosition(adapter.chats.size()-1);
    }

    private void eventHandler(){
        binding.btnExpand.setOnClickListener(v -> {
            setGuideHeight();
        });

        binding.etChat.setOnKeyListener((v, i, event) -> {
            if(event.getAction()== KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER){
                String msg = binding.etChat.getText().toString().trim();
                if(!msg.isEmpty()){
                    adapter.chats.add(msg);
                    adapter.notifyDataSetChanged();
                    binding.etChat.setText(null);
                    binding.chatRecycler.scrollToPosition(adapter.chats.size()-1);
                    return true;
                }else{
                    Toast.makeText(getContext(), "메세지를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
            return false;
        });
    }

    private void setGuideHeight(){
        boolean isExpanded = binding.btnExpand.getRotation() != 0;
        binding.btnExpand.setRotation(isExpanded ? 0 : 180);
        binding.guideLayout.getLayoutParams().height = isExpanded ? dpToPxConverter.convert(getContext(),47) : dpToPxConverter.convert(getContext(),87);
        binding.btnExpand.requestLayout();
        binding.guideLayout.requestLayout();
    }

    private void createTestData(){
        adapter.chats.clear();
        adapter.chats.add("곰세마리가 한 집에 있어 아빠곰 엄마곰 애기곰 아빠곰은 뚱뚱해 엄마곰은 날씬해 애기곰은 너무 귀여워 으쓱으쓱 잘한다.");
        adapter.chats.add("곰세마리");
        adapter.chats.add("곰세마리가 한 집에 있어 아빠곰");
        adapter.chats.add("곰세마리가 한");
        adapter.chats.add("곰세마리가 한 집에 있어 아빠곰 엄마곰 애기곰 아빠곰은 ");
        adapter.chats.add("곰세마리가 한 집에 있어 아빠곰 엄마곰 애기곰 아빠곰은 뚱뚱해 엄마곰은 날씬해 애기곰은 너무 귀여워 으쓱으쓱");
        adapter.chats.add("곰세마리가 한 집에 있어 아빠곰 엄마곰 애기곰 아빠곰은 뚱뚱해 엄마곰은");
        adapter.chats.add(".");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager)getContext().getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.etChat.getWindowToken(), 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
