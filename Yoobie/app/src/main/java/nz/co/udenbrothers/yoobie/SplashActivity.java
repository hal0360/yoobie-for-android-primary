package nz.co.udenbrothers.yoobie;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import nz.co.udenbrothers.yoobie.abstractions.RootActivity;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Screen;

public class SplashActivity extends RootActivity {

    private ImageView logo, dummyLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);
        dummyLogo = findViewById(R.id.dummyLogo);
    }

    public int getStatusBarHeight() {
        Rect rectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
        return rectangle.top;
    }

    @Override
    protected void onStart(){
        super.onStart();

        delay(1000, ()->{
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                Screen.height(displayMetrics.widthPixels - getStatusBarHeight());
                Screen.width(displayMetrics.heightPixels);
            } else{
                Screen.height(displayMetrics.heightPixels - getStatusBarHeight());
                Screen.width(displayMetrics.widthPixels);
            }
            Screen.density(displayMetrics.density);

            Animation anime = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_animation);
            logo.startAnimation(anime);
            logo.setVisibility(View.VISIBLE);
            dummyLogo.setVisibility(View.GONE);
        });

        delay(2000, ()->{
            if(Profile.userID() == null){
                //pushActivity(StartActivity.class);
               // delay(300, this::finish);
                toActivity(MainActivity.class);

            } else{
                toActivity(MainActivity.class);
            }
        });
    }
}
