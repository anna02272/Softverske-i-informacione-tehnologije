package com.example.vezbe6.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vezbe6.R;
import com.example.vezbe6.activities.SecondActivity;

import java.util.List;

public class DesignDemoRecyclerAdapter extends RecyclerView.Adapter<DesignDemoRecyclerAdapter.ViewHolder> {

    private List<String> mItems;

    public DesignDemoRecyclerAdapter(List<String> items) {
        mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        String item = mItems.get(i);
        viewHolder.mTextView.setText(item);

        viewHolder.mTextView.setOnClickListener(view -> {
            Context context = view.getContext();
            context.startActivity(new Intent(context, SecondActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;

        ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.list_item);
        }
    }

}
