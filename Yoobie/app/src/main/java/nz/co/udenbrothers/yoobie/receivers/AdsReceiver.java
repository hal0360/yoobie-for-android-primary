package nz.co.udenbrothers.yoobie.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import nz.co.udenbrothers.yoobie.services.YoobieService;
import nz.co.udenbrothers.yoobie.temps.Profile;
import nz.co.udenbrothers.yoobie.temps.Setting;

public class AdsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       // if(Profile.userID() != null && Setting.popup()){
            context.startService(new Intent(context, YoobieService.class));
       // }
    }
}
