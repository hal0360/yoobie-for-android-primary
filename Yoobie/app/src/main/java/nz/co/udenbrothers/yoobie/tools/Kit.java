package nz.co.udenbrothers.yoobie.tools;

import android.content.Context;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

import nz.co.udenbrothers.yoobie.interfaces.Filt;

public class Kit {

    public static void show(Context context, String mss){
        Toast.makeText(context, mss, Toast.LENGTH_LONG).show();
    }

    public static <T> List<T> filter(List<T> tList, Filt<T> filter){
        List<T> newList = new ArrayList<>();
        for(T t: tList){
            if(filter.exec(t)){
                newList.add(t);
            }
        }
        return newList;
    }

}
