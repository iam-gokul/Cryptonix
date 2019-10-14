package com.vit.app.cryptonix;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.vit.app.cryptonix.Modals.Message;

import java.util.List;

public class ChatAdapter extends ArrayAdapter<Message> {
    public ChatAdapter(Context context, int resource, List<Message> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.chat_bubble, parent, false);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView message = convertView.findViewById(R.id.message);

        Message msg = getItem(position);


        name.setText(msg.getName());
        message.setText(msg.getMessage());


        return convertView;
    }
}
