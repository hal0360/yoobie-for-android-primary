package nz.co.udenbrothers.yoobie;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nz.co.udenbrothers.yoobie.abstractions.RootFragment;


public class SignUpTwoFragment extends RootFragment {


    public SignUpTwoFragment() {
        super(R.layout.fragment_sign_up_two);
    }



    @Override
    public void created() {
        StartActivity activity = (StartActivity) parent;


    }

}
