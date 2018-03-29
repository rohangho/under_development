package com.example.android.doc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class mainChatUI extends AppCompatActivity {

    private ListView listView;
    private View btnSend;
    private EditText editText;
    boolean isMine = true;
    Button finalsubmit;
    private List<ChatMessage> chatMessages;
    private ArrayAdapter<ChatMessage> adapter;
    Button check;
    RelativeLayout lay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat_ui);
        chatMessages = new ArrayList<>();
        lay=(RelativeLayout)findViewById(R.id.onclickvisible);
        check=(Button)findViewById(R.id.submit);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setText("Response Recorded");
                Toast.makeText(getApplicationContext(),"Detail submitted to the doctor",Toast.LENGTH_LONG).show();
                lay.setVisibility(lay.VISIBLE);

            }
        });
        listView = (ListView) findViewById(R.id.list_msg);
        btnSend = findViewById(R.id.btn_chat_send);
        editText = (EditText) findViewById(R.id.msg_type);

        //set ListView adapter first
        adapter = new MessageAdapter(this, R.layout.chat_left, chatMessages);
        listView.setAdapter(adapter);

        //event for button SEND
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please input some text...", Toast.LENGTH_SHORT).show();
                } else {
                    //add message to list
                    ChatMessage chatMessage = new ChatMessage(editText.getText().toString(), isMine);
                    chatMessages.add(chatMessage);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                    if (isMine) {
                        isMine = false;
                    } else {
                        isMine = true;
                    }
                }
            }
        });
    }
}
