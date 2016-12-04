package com.sjsu.snappychat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {

    List<String> list = new ArrayList<String>();

    ListView listView;
    List<ChatItem> chatItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();

        chatItems = new ArrayList<ChatItem>();
        ChatItem item = new ChatItem(R.drawable.click, "Friend 1", R.drawable.greendot);
        ChatItem item2 = new ChatItem(R.drawable.click, "Friend 2 Friend 2", R.drawable.greendot);

        chatItems.add(item);
        chatItems.add(item2);
        chatItems.add(item2);
        chatItems.add(item2);
        chatItems.add(item2);
        chatItems.add(item2);
        chatItems.add(item2);
        chatItems.add(item2);
        chatItems.add(item2);

        listView = (ListView) findViewById(R.id.listView);
        CustomChatVewAdapter adapter = new CustomChatVewAdapter(this, R.layout.chat_list_item, chatItems);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Toast toast = Toast.makeText(getApplicationContext(),
                "Item " + (position + 1) + ": " + chatItems.get(position),
                Toast.LENGTH_SHORT);
        toast.show();
        startChatSession();
        //ChatItem item = chatItems.get(position);
        //Intent passer = new Intent(this,ChatClick.class);
        //passer.putExtra("name",item.getTitle());

    }

    private void startChatSession(){
        setContentView(R.layout.activity_chat_click);

        List<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
        ChatMessage itemhim = new ChatMessage(R.drawable.click,"HIM");
        ChatMessage item = new ChatMessage(R.drawable.click, "ME");

        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(item);
        chatMessages.add(itemhim);
        chatMessages.add(itemhim);

        listView = (ListView) findViewById(R.id.chat_listView);
        CustomChatMessageAdapter adapter = new CustomChatMessageAdapter(this, R.layout.chat_message, chatMessages);
        listView.setAdapter(adapter);
        //listView.setOnItemClickListener(this);
    }
}
