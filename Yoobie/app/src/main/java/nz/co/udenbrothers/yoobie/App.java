package nz.co.udenbrothers.yoobie;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;

import nz.co.udenbrothers.yoobie.tools.sqlUtils.SqlAccess;


public class App extends Application {

    private static SharedPreferences p;
    private static SharedPreferences.Editor editor;
    private static SqlAccess sqlAccess;

    @SuppressLint("CommitPrefEdits")
    @Override
    public void onCreate() {
        super.onCreate();
        p = getSharedPreferences("app", MODE_PRIVATE);
        editor = p.edit();
        sqlAccess = SqlAccess.getInstance(this);
        sqlAccess.makeDB();
    }

    public static SqlAccess getSqlDatebase(){
        return sqlAccess;
    }

    public static void putStr(String key, String val){
        editor.putString(key, val);
        editor.apply();
    }

    public static String getStr(String key, String def){
        return p.getString(key, def);
    }

    public static void putInt(String key, int val){
        editor.putInt(key, val);
        editor.apply();
    }

    public static int getInt(String key, int def){
        return p.getInt(key, def);
    }

    public static void putLong(String key, long val){
        editor.putLong(key, val);
        editor.apply();
    }

    public static long getLong(String key, long def){
        return p.getLong(key, def);
    }

    public static void putFloat(String key, float val){
        editor.putFloat(key, val);
        editor.apply();
    }

    public static float getFloat(String key, float def){
        return p.getFloat(key, def);
    }

    public static void putBool(String key, Boolean val){
        editor.putBoolean(key, val);
        editor.apply();
    }

    public static Boolean getBool(String key, Boolean def){
        return p.getBoolean(key, def);
    }

    public static void clear(){
        editor.clear();
        editor.apply();
    }

    public static void clearSQL(){
        sqlAccess.dropAll();
    }
}
