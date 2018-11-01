package nz.co.udenbrothers.yoobie.tools.sqlUtils;

import android.database.Cursor;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nz.co.udenbrothers.yoobie.App;
import nz.co.udenbrothers.yoobie.abstractions.SqlEntity;
import nz.co.udenbrothers.yoobie.interfaces.Filt;


public class Sql {

    public static final int VERSION = 1;
    public static final String DBNAME = "yoobieDB";


    private static String cursorToString(Cursor cursor) {
        JSONArray arr = new JSONArray();

        try {
            while (cursor.moveToNext()) {
                int nColumns = cursor.getColumnCount();
                JSONObject row = new JSONObject();
                for (int i = 0 ; i < nColumns ; i++) {
                    String colName = cursor.getColumnName(i);
                    if (colName != null) {
                        switch (cursor.getType(i)) {
                            case Cursor.FIELD_TYPE_BLOB   : row.put(colName, Arrays.toString(cursor.getBlob(i))); break;
                            case Cursor.FIELD_TYPE_FLOAT  : row.put(colName, cursor.getDouble(i))         ; break;
                            case Cursor.FIELD_TYPE_INTEGER: row.put(colName, cursor.getLong(i))           ; break;
                            case Cursor.FIELD_TYPE_NULL   : row.put(colName, null)                     ; break;
                            case Cursor.FIELD_TYPE_STRING : row.put(colName, cursor.getString(i))         ; break;
                        }
                    }
                }
                arr.put(row);
            }
        }catch (Exception e) {
            Log.e("Error json from cursor",e+"");
        }finally {
            cursor.close();
        }

        return arr.toString();
    }

    public static <T extends SqlEntity> List<T> get(Class<T> classes, Filt<T> filt){
        SqlAccess sqlAccess = App.getSqlDatebase();
        Cursor cursor = sqlAccess.get(classes.getSimpleName());
        Gson gson = new Gson();
        List<T> newList = new ArrayList<>();
        if(cursor == null) return newList;
        List<T> listt = gson.fromJson(cursorToString(cursor), new ListOfJson<>(classes));
        for(T t: listt){
            if(filt.exec(t)){
                newList.add(t);
            }
        }
        return newList;
    }

    public static <T extends SqlEntity> void clear(Class<T> tClass){
        SqlAccess sqlAccess = App.getSqlDatebase();
        sqlAccess.clear(tClass.getSimpleName());
    }

    public static void clearAll(){
        SqlAccess sqlAccess = App.getSqlDatebase();
        sqlAccess.dropAll();
    }

    public static <T extends SqlEntity> List<T> get(Class<T> classes){
        SqlAccess sqlAccess = App.getSqlDatebase();
        Cursor cursor = sqlAccess.get(classes.getSimpleName());
        if(cursor == null) return new ArrayList<>();
        Gson gson = new Gson();
        return gson.fromJson(cursorToString(cursor), new ListOfJson<>(classes));
    }
}
