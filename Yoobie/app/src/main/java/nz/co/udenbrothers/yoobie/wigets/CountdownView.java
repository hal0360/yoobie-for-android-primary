package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nz.co.udenbrothers.yoobie.R;


public class CountdownView extends RelativeLayout {

    private Handler handler;
    private TextView tDay, tHour, tMin, tSec;
    private Date futureDate;
    private Date currentDate;

    public CountdownView(Context context) {
        super(context);
        init(context, null);
    }

    public CountdownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.countdown_layout, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);
        handler = new Handler();
        tDay = findViewById(R.id.txtTimerDay);
        tHour = findViewById(R.id.txtTimerHour);
        tMin = findViewById(R.id.txtTimerMinute);
        tSec = findViewById(R.id.txtTimerSecond);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacksAndMessages(null);
    }

    public void countDownStop(){
        handler.removeCallbacksAndMessages(null);
    }

    public void countDownStart(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            futureDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                currentDate = new Date();
                if (!currentDate.after(futureDate)) {
                    long diff = futureDate.getTime() - currentDate.getTime();
                    long days = diff / (24 * 60 * 60 * 1000);
                    diff -= days * (24 * 60 * 60 * 1000);
                    long hours = diff / (60 * 60 * 1000);
                    diff -= hours * (60 * 60 * 1000);
                    long minutes = diff / (60 * 1000);
                    diff -= minutes * (60 * 1000);
                    long seconds = diff / 1000;
                    tDay.setText(String.format(Locale.US,"%02d", days));
                    tHour.setText(String.format(Locale.US,"%02d", hours));
                    tMin.setText(String.format(Locale.US,"%02d", minutes));
                    tSec.setText(String.format(Locale.US,"%02d", seconds));
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }
}
