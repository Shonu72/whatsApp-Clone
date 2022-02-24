package com.coolblogger.tech.whatsapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.coolblogger.tech.whatsapp.Fragements.CallsFragment;
import com.coolblogger.tech.whatsapp.Fragements.StatusFragment;
import com.coolblogger.tech.whatsapp.Fragements.chatFragement;

public class FragmentsAdapter extends FragmentStateAdapter {

    private String[] titles = {"chats", "status", "calls"};

    public FragmentsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new chatFragement();
            case 1: return new StatusFragment();
            case 2: return new CallsFragment();

        }
        return new chatFragement();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }


}
