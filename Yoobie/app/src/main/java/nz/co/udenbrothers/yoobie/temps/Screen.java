package nz.co.udenbrothers.yoobie.temps;

import nz.co.udenbrothers.yoobie.App;


public class Screen {
    public static int width(){
        return App.getInt("width",300);
    }

    public static void width(int val){
        App.putInt("width", val);
    }

    public static int height(){
        return App.getInt("height",500);
    }

    public static void height(int val){
        App.putInt("height", val);
    }

    public static int lastPosX(){
        return App.getInt("lastPosX", 0);
    }

    public static void lastPosX(int val){
        App.putInt("lastPosX", val);
    }

    public static int lastPosY(){
        return App.getInt("lastPosY", 0);
    }

    public static void lastPosY(int val){
        App.putInt("lastPosY", val);
    }

    public static float density(){
        return App.getFloat("density",1.0f);
    }

    public static void density(float val){
        App.putFloat("density", val);
    }
}
