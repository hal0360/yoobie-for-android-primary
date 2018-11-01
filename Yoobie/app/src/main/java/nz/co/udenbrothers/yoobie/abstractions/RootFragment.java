package nz.co.udenbrothers.yoobie.abstractions;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;

import nz.co.udenbrothers.yoobie.interfaces.CmdView;

public abstract class RootFragment extends Fragment {

    private View fragView;
    private int rid;
    protected RootActivity parent;

    public RootFragment(int id){
        rid = id;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragView = inflater.inflate(rid, container, false);
        created();
        return fragView;
    }

    public abstract void created();

    protected final <T extends View & Checkable> T findViewById(int Rid){
        return fragView.findViewById(Rid);
    }

    protected final void clicked(View v, CmdView cd){
        v.setOnClickListener(cd::exec);
    }

    protected final void clicked(int id, CmdView cd){
        findViewById(id).setOnClickListener(cd::exec);
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        parent = (RootActivity) activity;
    }

    public void onBackPressed() {

    }
}
