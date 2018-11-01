package nz.co.udenbrothers.yoobie.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import nz.co.udenbrothers.yoobie.R;
import nz.co.udenbrothers.yoobie.entities.Campaign;
import nz.co.udenbrothers.yoobie.entities.Stamp;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Screen;
import nz.co.udenbrothers.yoobie.temps.Setting;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.Sql;
import nz.co.udenbrothers.yoobie.wigets.YoobieImage;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class YoobieService extends Service {

    private WindowManager windowManager;
    private YoobieImage ads;
    private WindowManager.LayoutParams params;
    private Campaign targetCampaign;
    private Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        targetCampaign = null;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        ads = new YoobieImage(this);
        handler = new Handler();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        ads.setScaleType(ImageView.ScaleType.FIT_XY);
        int winType;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) winType = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        else winType = WindowManager.LayoutParams.TYPE_PHONE;
        params = new WindowManager.LayoutParams(
                Screen.width()/2,
                Screen.width()/2,
                winType,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.START;
        params.x = Screen.lastPosX();
        params.y = Screen.lastPosY();

        switch (Setting.animation()) {
            case 0:
                params.windowAnimations = android.R.style.Animation_Translucent;
                break;
            case 1:
                params.windowAnimations = android.R.style.Animation_Dialog;
                break;
            case 2:
                params.windowAnimations = android.R.style.Animation_Toast;
                break;
        }

        ads.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        if (ads.isShown()) windowManager.updateViewLayout(ads, params);
                        break;
                }
                return v.performClick();
            }
        });

        /*
        targetCampaign = getRamCampaign();
        if (targetCampaign != null) {
            ads.setImage(getExternalFilesDir(null) + "/" + targetCampaign.fullImageId);
        }else{
            ads.setImgRes(R.drawable.oreo);
        }*/

        ads.setImgRes(R.drawable.oreo);
        windowManager.addView(ads, params);

        handler.postDelayed(() -> this.stopSelf(), 4000);

        return START_NOT_STICKY;
    }

    private Campaign getRamCampaign(){
        List<Campaign> campaigns = Sql.get(Campaign.class);
        if(campaigns.size() == 0) return null;
        else{
            Random rand = new Random();
            int rann = rand.nextInt(campaigns.size());
            return campaigns.get(rann);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (targetCampaign != null) {
            Stamp stamp = new Stamp();
            stamp.campaignId = targetCampaign.id;
            stamp.viewedOn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US).format(new Date());
            Profile.lastAdsId(targetCampaign.fullImageId);
        }
        if (ads.isShown()){
            windowManager.removeView(ads);
        }
        Screen.lastPosX(params.x);
        Screen.lastPosY(params.y);
    }
}
