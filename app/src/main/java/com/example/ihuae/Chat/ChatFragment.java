package com.example.ihuae.Chat;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ihuae.Util.DBContract;
import com.example.ihuae.Util.DpToPxConverter;
import com.example.ihuae.Util.MainDBHelper;
import com.example.ihuae.databinding.FragmentChatBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ChatFragment extends Fragment {

    private FragmentChatBinding binding;
    private ChatAdapter adapter;
    private DpToPxConverter dpToPxConverter;
    private MainDBHelper dbHelper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    private SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        dbHelper = new MainDBHelper(getContext());

        adapter = new ChatAdapter(getContext());
        binding.chatRecycler.setAdapter(adapter);
        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        //createTestData();       //test
        selectChatData();
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
                    insertChatData(msg);
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

    private void insertChatData(String msg){
        HashMap<String, Object> m = new HashMap<>();
        Calendar c = Calendar.getInstance();
        Date d = c.getTime();
        String today = sdf2.format(d);
        c.add(Calendar.DATE, 1);
        Date dy = c.getTime();
        String yesterday = sdf2.format(dy);
        int dateID = Integer.parseInt(sdf.format(d));
        m.put(DBContract.MessageEntry.COLUMN_NAME_1, dateID);
        m.put(DBContract.MessageEntry.COLUMN_NAME_2, msg);
        m.put(DBContract.MessageEntry.COLUMN_NAME_3, today);
        m.put(DBContract.MessageEntry.COLUMN_NAME_4, yesterday);
        boolean isSuccess = dbHelper.insertData(DBContract.MessageEntry.TABLE_NAME, m);
        if(isSuccess) Log.e("===============Success insertChatData================", "dateID = " + dateID);
        else Log.e("===============Fail insertChatData================", "dateID = " + dateID);
    }

    private void selectChatData(){
        String sql = "SELECT " + DBContract.MessageEntry.COLUMN_NAME_2
                    + " FROM " + DBContract.MessageEntry.TABLE_NAME
                    + " WHERE datetime(" + DBContract.MessageEntry.COLUMN_NAME_4 + ") > datetime('now','localtime')";
        Cursor c = dbHelper.selectData(sql);
        while (c.moveToNext()){
            String txt = c.getString(0);
            adapter.chats.add(txt);
        }
        adapter.notifyDataSetChanged();
        c.close();
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
