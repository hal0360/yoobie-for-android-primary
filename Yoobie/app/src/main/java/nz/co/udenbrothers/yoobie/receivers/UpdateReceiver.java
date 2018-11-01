package nz.co.udenbrothers.yoobie.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import nz.co.udenbrothers.yoobie.services.UploadService;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class UpdateReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        startWakefulService(context, new Intent(context, UploadService.class));
    }

    public static void starting(Context context)
    {
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent("yoobieSendStamp");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        if (am != null) {
            am.cancel(pi);
            am.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 7200000, pi);
        }
    }

    public static void cancelling(Context context)
    {
        Intent intent = new Intent("yoobieSendStamp");
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.cancel(sender);
        }
        sender.cancel();
    }
}
