package com.example.vezbe10_2.fragments;



import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.vezbe10_2.MainActivity;
import com.example.vezbe10_2.R;
import com.example.vezbe10_2.adapters.MessageAdapter;
import com.example.vezbe10_2.model.Message;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;



public class MessageFragment extends Fragment {

    private MessageAdapter adapter;
    private EditText editMessage;
    private Button sendMessage;
    private ListView lw;

    public static MessageFragment newInstance() {
        MessageFragment mpf;
        mpf = new MessageFragment();

        return mpf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        View vi = inflater.inflate(R.layout.fragment_message_list, vg, false);

        adapter = new MessageAdapter(getContext(), new ArrayList<Message>());
        editMessage = (EditText) vi.findViewById(R.id.editMessage);
        sendMessage = vi.findViewById(R.id.button2);
        lw = vi.findViewById(R.id.messages_list_view);
        lw.setAdapter(adapter);

        sendMessage.setOnClickListener(v -> {
            MainActivity.socket.emit("message", editMessage.getText());
        });

        return vi;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MainActivity.socket.on("message", args -> {
            if(args[0] != null){
                Log.i("SOCKET", args[0].toString());

                List<Message> messages = convertJsonToListOfObject(args[0]);
                Log.i("SOCKET", "PORUKE: " + messages);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new MessageAdapter(getContext(), messages);
                        lw.setAdapter(adapter);
                    }
                });
            }
        });
    }

    public static List<Message> convertJsonToListOfObject(Object obj) {
        List<Message> messages = new ArrayList<>();

        JSONArray array = new JSONArray();
        array = (JSONArray) obj;
//                Log.i("SOCKET", "PORUKE " + array.toString());
        for(int i = 0; i < array.length(); i++){
            try {
//                        Log.i("SOCKET", "array is " + array.get(i));
                Gson gson = new Gson();
                Message m = gson.fromJson(array.get(i).toString(), Message.class);
                messages.add(m);
                Log.i("SOCKET", "-- " + m.getText().toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return messages;
    }

}