package com.example.vezbe10_1.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vezbe10_1.R;
import com.example.vezbe10_1.model.Message;

import java.util.ArrayList;
import java.util.List;


public class MessageAdapter extends BaseAdapter {
    private Context context;
    private List<Message> messages = new ArrayList<>();


    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Message message = messages.get(position);

        Log.i("REZ", "ADAPTER ID: "+ message.getId().toString() + ", Text " + message.getText());

        if (vi == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(message.getSender()){
                vi = inflater.inflate(R.layout.message_list, null);
            }else{
                vi = inflater.inflate(R.layout.message_list_2, null);
            }
        }

        TextView text = vi.findViewById(R.id.message_text);

        text.setText(message.getText());

        return vi;
    }
}
