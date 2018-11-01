package nz.co.udenbrothers.yoobie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.co.udenbrothers.yoobie.abstractions.RootFragment;


public class SignInFragment extends RootFragment {

    private StartActivity activity;

    public SignInFragment() {
        super(R.layout.fragment_sign_in);
    }

    @Override
    public void created() {
        activity = (StartActivity) parent;
        activity.setTitle("SIGN IN");

    }



}
