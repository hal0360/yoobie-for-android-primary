package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import nz.co.udenbrothers.yoobie.R;

public class YoobieText extends RelativeLayout{

    public YoobieText(Context context) {
        super(context);
        init(context, null);
    }

    public YoobieText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        View.inflate(context, R.layout.yoobie_text_layout, this);
        setDescendantFocusability(FOCUS_BLOCK_DESCENDANTS);

        ImageView icon = findViewById(R.id.icon);
        TextView text = findViewById(R.id.text);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.YoobieText,
                    0, 0);

            String str = "";
            Drawable iconImg = null;

            try {
                iconImg = a.getDrawable(R.styleable.YoobieText_textIcon);
                str = a.getString(R.styleable.YoobieText_text);
            } catch (Exception e) {
                Log.e("YoobieText", "There was an error loading attributes.");
            } finally {
                a.recycle();
            }

            text.setText(str);
            icon.setBackgroundDrawable(iconImg);
        }
    }
}
