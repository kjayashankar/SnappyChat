package com.sjsu.snappychat;

/**
 * Created by jay on 12/3/16.
 */

public class ChatMessage {
    private int imageID;
    private String chatText;

    public ChatMessage(int imageID,String chatText){
        this.imageID = imageID;
        this.chatText = chatText;
    }


    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }
}
