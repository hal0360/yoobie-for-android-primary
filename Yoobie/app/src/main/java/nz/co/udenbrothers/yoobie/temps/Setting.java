package nz.co.udenbrothers.yoobie.temps;

import nz.co.udenbrothers.yoobie.App;

public class Setting {

    public static int animation(){
        return App.getInt("animation",0);
    }

    public static void animation(int val){
        App.putInt("animation", val);
    }

    public static int timing(){
        return App.getInt("timing",0);
    }

    public static void timing(int val){
        App.putInt("timing", val);
    }

    public static boolean wifi(){
        return App.getBool("wifi",false);
    }

    public static void wifi(Boolean val){
        App.putBool("wifi", val);
    }

    public static boolean popup(){
        return App.getBool("popup",true);
    }

    public static void popup(Boolean val){
        App.putBool("popup", val);
    }
}
