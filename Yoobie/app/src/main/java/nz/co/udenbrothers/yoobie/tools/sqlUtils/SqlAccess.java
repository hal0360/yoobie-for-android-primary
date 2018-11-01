package nz.co.udenbrothers.yoobie.tools.sqlUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class SqlAccess extends SQLiteOpenHelper {

    private static SqlAccess sInstance;
    private SQLiteDatabase db;

    private SqlAccess(Context context) {
        super(context, Sql.DBNAME, null, Sql.VERSION);
    }

    public static synchronized SqlAccess getInstance(Context context) {
        if (sInstance == null) sInstance = new SqlAccess(context);
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    public void createTable(String sqls) {
        db.execSQL(sqls);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropAll();
    }

    public int update(String table, ContentValues cv, String field, String value){
        int rowUpdate =  db.update(table, cv, field + "=" + value, null);
        if(rowUpdate < 1)  Log.e("SQL update error", "Cannot save entity");
        return db.update(table, cv, field + "=" + value, null);
    }

    public void dropAll(){
        Cursor c = db.rawQuery("SELECT name FROM " + Sql.DBNAME + " WHERE type='table'", null);
        List<String> tables = new ArrayList<>();
        while (c.moveToNext()) {
            tables.add(c.getString(0));
        }
        for (String table : tables) {
            String dropQuery = "DROP TABLE IF EXISTS " + table;
            db.execSQL(dropQuery);
        }
        c.close();
    }

    public long add(String table, ContentValues cv){
        return db.insert(table, null, cv);
    }

    public Cursor get(String table) {
        try{
            String query = "SELECT  * FROM " + table;
            return db.rawQuery(query, null);
        }
        catch (SQLiteException e) {
            return null;
        }
    }

    public void delete(String table, String field, String value){
        db.execSQL("DELETE FROM " + table + " WHERE " + field + "= '" + value + "'");
    }

    public void clear(String table){
        String dropQuery = "DROP TABLE IF EXISTS " + table;
        db.execSQL(dropQuery);
       // db.delete(table, null, null);
    }

    public void makeDB(){
        db = getWritableDatabase();
    }
}
