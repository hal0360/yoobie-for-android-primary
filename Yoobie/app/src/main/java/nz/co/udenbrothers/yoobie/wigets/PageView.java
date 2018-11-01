package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import java.util.ArrayList;
import java.util.List;

public class PageView extends ViewPager{

    private AppCompatActivity context;
    private MyAdapter mSectionsPagerAdapter;

    public PageView(Context context)
    {
        super(context);
        this.context = (AppCompatActivity) context;
        mSectionsPagerAdapter = new MyAdapter(this.context.getSupportFragmentManager());
        this.setAdapter(mSectionsPagerAdapter);
    }

    public PageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = (AppCompatActivity) context;
        mSectionsPagerAdapter = new MyAdapter(this.context.getSupportFragmentManager());
        this.setAdapter(mSectionsPagerAdapter);
    }

    public void add(Fragment frag){
        mSectionsPagerAdapter.fragments.add(frag);
        mSectionsPagerAdapter.notifyDataSetChanged();
    }

    public void addTabListner(TabLayout tabLayout){
        this.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private class MyAdapter extends FragmentPagerAdapter {

        public List<Fragment> fragments = new ArrayList<>();

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
