package nz.co.udenbrothers.yoobie;

import android.os.Bundle;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.wigets.CountdownView;
import nz.co.udenbrothers.yoobie.wigets.WaveView;

public class StartActivity extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        CountdownView countdownView = findViewById(R.id.countdown);
        countdownView.countDownStart("2019-05-22");
        clicked(R.id.signInButton, v -> pushActivity(SignInActivity.class));
        clicked(R.id.signUpButton, v -> pushActivity(SignUp1Activity.class));
        clicked(R.id.facebookButton, v -> {

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        WaveView waveView = findViewById(R.id.wave);
        delay(300, () -> waveView.setProgress(0.6f));
    }
}
