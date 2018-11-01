package nz.co.udenbrothers.yoobie;


import nz.co.udenbrothers.yoobie.abstractions.RootFragment;
import nz.co.udenbrothers.yoobie.wigets.CountdownView;


public class StartFragment extends RootFragment {

    public StartFragment() {
        super(R.layout.fragment_start);
    }

    @Override
    public void created() {
        StartActivity activity = (StartActivity) parent;
        CountdownView countdown = findViewById(R.id.countdown);

        countdown.countDownStart("2018-11-22");

       // clicked(R.id.signInButton, v-> activity.toFragment(new SignInFragment()));
       // clicked(R.id.signUpButton, v-> activity.toFragment(new SignUpFragment()));

        //activity.waveProgress(60);
    }

}
