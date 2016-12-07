package com.example.vsaik.snapchat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AllFriendsChatActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listAllFriends = null;
    private Context context;
    List<ChatItem> activeFriends = null;
    String myName = "jay";
    JSONArray responseFetch = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activeFriends = new ArrayList<ChatItem>();
        setContentView(R.layout.activity_all_friends_chat);
        listAllFriends = (ListView) findViewById(R.id.listViewAll);
        context = this;
        onStart();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG","On start method");
        onResume();

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAg","On resume method");

       // activeFriends = getAllFriends();
        RetrieveFriends retrieveFriends = new RetrieveFriends();
        retrieveFriends.execute();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String chatSession = activeFriends.get(position).getTitle();
        Intent i = new Intent(this,StartChatActivity.class);
        i.putExtra("friend",chatSession);
        startActivity(i);
    }

    private void showFriends(){

        CustomChatVewAdapter adapter = new CustomChatVewAdapter(this, R.layout.chat_list_item, activeFriends);
        listAllFriends.setAdapter(adapter);
        listAllFriends.setOnItemClickListener(this);
    }


    class RetrieveFriends extends
            AsyncTask<Void,Void,Void>{



        @Override
        protected Void doInBackground(Void... voids) {

            //you get a json object
            //iterate over the json data and add data like this
            //convert string to bitmap


            HashMap<String,String> hashMap = new HashMap<String, String>();
            hashMap.put("username",myName);
            hashMap.put("URL",Constants.URL+"/get_added_friends");
            hashMap.put("Method","GET");
            PostData fecth = new PostData(hashMap);
            try {
                responseFetch = new JSONArray(fecth.doInBackground());
                Log.e("RESPONSE",responseFetch.toString());

            }
            catch(Exception e){
                Log.e("RESPONSE- ERROR",e.toString());
            }


          /*  String base64Image = "";
            Bitmap friend = ImageUtils.getBitmapFromBase64(base64Image);
            String name = "";
             //1 and 0 based on online, offline
            int status = ImageUtils.getStatus("Online");
            ChatItem item1 = new ChatItem(friend,name,status);*/
            int size = responseFetch.length();
            for(int i = 0 ; i < size ; i++){
                ChatItem item = null;
                try {
                    JSONObject object = responseFetch.getJSONObject(i);
                    if (myName.equalsIgnoreCase(object.getString("sender"))) {

                        ///// TO DO - FETCH IMAGE
                        item = new ChatItem(null, object.getString("friend"),ImageUtils.getStatus("Online"));
                    } else {
                        item = new ChatItem(null, object.getString("friend"),ImageUtils.getStatus("Online"));
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                activeFriends.add(item);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            showFriends();
        }
    }

}
