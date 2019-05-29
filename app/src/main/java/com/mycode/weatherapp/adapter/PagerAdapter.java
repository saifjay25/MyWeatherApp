package com.mycode.weatherapp.adapter;

import android.annotation.SuppressLint;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mycode.weatherapp.tabs.Tab1;
import com.mycode.weatherapp.tabs.Tab2;
import com.mycode.weatherapp.tabs.Tab3;
import com.mycode.weatherapp.tabs.Tab4;
import com.mycode.weatherapp.tabs.Tab5;
import com.mycode.weatherapp.tabs.Tab6;
import com.mycode.weatherapp.tabs.Tab7;

import java.util.HashMap;
import java.util.Map;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numTabs;

    @SuppressLint("UseSparseArrays")
    public PagerAdapter(FragmentManager fm, int numTabs)
    {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position)
    {
        switch(position)
        {
            case 0:
                return new Tab1();
            case 1:
                return new Tab2();
            case 2:
                return new Tab3();
            case 3:
                return new Tab4();
            case 4:
                return new Tab5();
            case 5:
                return new Tab6();
            case 6:
                return new Tab7();
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return numTabs;
    }

}
