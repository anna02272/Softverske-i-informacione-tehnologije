package com.example.vezbe10_1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.vezbe10_1.R;
import com.example.vezbe10_1.adapters.MessageAdapter;
import com.example.vezbe10_1.model.Message;
import com.example.vezbe10_1.service.ServiceUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MessageFragment extends Fragment {

    private MessageAdapter adapter;
    private EditText editMessage;
    private Button sendMessage;
    private ListView lw;
    private List<Message> messages;

    public static MessageFragment newInstance() {
        MessageFragment mpf;
        mpf = new MessageFragment();

        return mpf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {
        setHasOptionsMenu(true);
        View vi = inflater.inflate(R.layout.fragment_message_list, vg, false);

        adapter = new MessageAdapter(getContext(), new ArrayList<>());

        sendMessage = vi.findViewById(R.id.button2);
        lw = vi.findViewById(R.id.messages_list_view);
        lw.setAdapter(adapter);



        return vi;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Call<List<Message>> messagesList = ServiceUtils.messageRestService.getMessages();
        messagesList.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                messages = (List<Message>) response.body();
                Log.d("REZ", String.valueOf(messages));
                Log.i("REZ", "PORUKE: " + messages);

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new MessageAdapter(getContext(), messages);
                        lw.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d("REZ","onFailure");

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        View vi = getView();
        sendMessage.setOnClickListener(v -> {
            editMessage = vi.findViewById(R.id.editMessage);
            Log.d("REZ",editMessage.getText().toString());

            Call<Message> call = ServiceUtils.messageRestService.sendMessage(editMessage.getText().toString());
            call.enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    Log.d("REZ", response.body().getText());
                    messages.add(((Message) response.body()));
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                    Log.d("REZ",t.getMessage().toString());
                }
            });

        });


    }
}