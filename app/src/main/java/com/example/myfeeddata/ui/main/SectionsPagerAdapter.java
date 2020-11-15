package com.example.myfeeddata.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myfeeddata.FeedProductFragment;
import com.example.myfeeddata.FeedStockFragment;
import com.example.myfeeddata.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};

    private static final int[] TAB_ICONS = new int[]{R.drawable.ic_product, R.drawable.ic_stock};

    private static final int PAGES = TAB_TITLES.length;

    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FeedProductFragment();
            default:
                return new FeedStockFragment();
        }
    }


    @Override
    public int getCount() {
        // Show 2 total pages.
        return PAGES;
    }

    public View getTabView(int position) {
        View _view = LayoutInflater.from(mContext).inflate(R.layout.custom_tab, null);

        TextView titleTV = _view.findViewById(R.id.title);
        titleTV.setText(TAB_TITLES[position]);

        ImageView iconIM = _view.findViewById(R.id.icon);
        iconIM.setImageResource(TAB_ICONS[position]);

        return _view;
    }
}