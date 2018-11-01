package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

import nz.co.udenbrothers.yoobie.interfaces.CmdTab;


public class TabView extends TabLayout implements TabLayout.OnTabSelectedListener {

    private CmdTab cmDSec;
    private CmdTab cmDUnSec;

    public TabView(Context context)
    {
        super(context);
    }

    public TabView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void addPageListner(PageView viewPager){
        viewPager.addTabListner(this);
        this.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                cmDSec.exec(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                cmDUnSec.exec(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    public void selected(CmdTab cmd){
        cmDSec = cmd;
    }

    public void unSelected(CmdTab cmd){
        cmDUnSec = cmd;
    }

    @Override
    public void onTabSelected(Tab tab) {
        cmDSec.exec(tab);
    }

    @Override
    public void onTabUnselected(Tab tab) {
        cmDUnSec.exec(tab);
    }

    @Override
    public void onTabReselected(Tab tab) {}
}

