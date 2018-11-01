package nz.co.udenbrothers.yoobie.wigets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import nz.co.udenbrothers.yoobie.R;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;


public class YoobieImage extends GifImageView{

    Context context;

    public YoobieImage(Context context) {
        super(context);
        this.context = context;
    }

    public YoobieImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setImage(String path){
        try{
            setImageDrawable(new GifDrawable(path));
        }
        catch (Exception e){
            try{
                setImageDrawable(Drawable.createFromPath(path));
            }catch (Exception ee){
                Log.e("Error loading image",e+"");
                setImageResource(R.drawable.notaval);
            }
        }
    }

    public void setImgRes(int res){
        setImageResource(res);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }
}
