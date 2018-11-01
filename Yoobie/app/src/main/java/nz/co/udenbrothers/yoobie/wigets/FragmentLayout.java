package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class FragmentLayout extends FrameLayout {

    private android.support.v4.app.FragmentManager manager;
    private Fragment topFragment;

    public FragmentLayout(Context context)
    {
        super(context);
        manager = ((AppCompatActivity) context).getSupportFragmentManager();
    }

    public FragmentLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        manager = ((AppCompatActivity) context).getSupportFragmentManager();
    }

    public void replace(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transaction.replace(getId(), fragment);
        transaction.commit();
        topFragment = fragment;
    }

    public void add(Fragment fragment) {
        FragmentTransaction transactionn = manager.beginTransaction();
        transactionn.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        transactionn.add(getId(), fragment);
        transactionn.commit();
        topFragment = fragment;
    }

    public Fragment getTopFragment(){
        return topFragment;
    }
}
