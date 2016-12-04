package com.sjsu.snappychat;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jay on 12/3/16.
 */

public class CustomChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    Context context;

    public CustomChatMessageAdapter(Context context, int resourceId,
                                List<ChatMessage> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView chatText;
        TextView chatTextLeft;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        CustomChatMessageAdapter.ViewHolder holder = null;
        ChatMessage rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.chat_message, null);
            holder = new ViewHolder();
            holder.chatText = (TextView) convertView.findViewById(R.id.chat_text);
            holder.imageView = (ImageView) convertView.findViewById(R.id.icon);
            holder.chatTextLeft = (TextView) convertView.findViewById(R.id.chat_text_left);
            convertView.setTag(holder);
        } else
            holder = (CustomChatMessageAdapter.ViewHolder) convertView.getTag();

        if(rowItem.getChatText().equalsIgnoreCase("ME")) {
            RelativeLayout.LayoutParams paramsImage = (RelativeLayout.LayoutParams) holder.imageView.getLayoutParams();
            paramsImage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            holder.imageView.setLayoutParams(paramsImage);
            holder.imageView.setImageResource(rowItem.getImageID());
            holder.chatTextLeft.setText(rowItem.getChatText());
        }
        else{
            holder.chatText.setText(rowItem.getChatText());
            holder.imageView.setImageResource(rowItem.getImageID());

        }

        return convertView;
    }
    @Override
    public int getViewTypeCount() {

        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }
}
