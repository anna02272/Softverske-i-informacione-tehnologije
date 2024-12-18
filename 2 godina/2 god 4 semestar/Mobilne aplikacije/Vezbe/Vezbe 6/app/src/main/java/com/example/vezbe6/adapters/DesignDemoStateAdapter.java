package com.example.vezbe6.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.vezbe6.fragments.DesignDemoFragment;
import com.example.vezbe6.fragments.FormDemoFragment;

public class DesignDemoStateAdapter extends FragmentStateAdapter {
    public DesignDemoStateAdapter(FragmentActivity fm) {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0 || position == 2)

            return DesignDemoFragment.newInstance(position);
        else if (position == 1)
            return FormDemoFragment.newInstance();
        else
            return null;
    }
    @Override
    public int getItemCount() {
        return 3;
    }
}
