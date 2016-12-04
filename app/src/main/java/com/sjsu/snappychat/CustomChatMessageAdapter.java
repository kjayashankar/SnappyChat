package com.sjsu.snappychat;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        ImageView displayPicture;
        ImageView chatPicture;
        ImageView chatPictureLeft;
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
            holder.displayPicture = (ImageView) convertView.findViewById(R.id.icon);
            holder.chatTextLeft = (TextView) convertView.findViewById(R.id.chat_text_left);
            holder.chatPicture = (ImageView) convertView.findViewById(R.id.chat_image);
            convertView.setTag(holder);
        } else
            holder = (CustomChatMessageAdapter.ViewHolder) convertView.getTag();

        if(rowItem.getUser().equalsIgnoreCase("ME")) {
            RelativeLayout.LayoutParams paramsImage = (RelativeLayout.LayoutParams) holder.displayPicture.getLayoutParams();
            paramsImage.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            holder.displayPicture.setLayoutParams(paramsImage);
            holder.displayPicture.setImageResource(rowItem.getDisplayImage());
            holder.chatTextLeft.setText(rowItem.getChatText());
        }
        else{
            holder.chatPicture.setImageResource(rowItem.getChatImage());
            holder.chatText.setText(rowItem.getChatText());
            holder.displayPicture.setImageResource(rowItem.getDisplayImage());

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
