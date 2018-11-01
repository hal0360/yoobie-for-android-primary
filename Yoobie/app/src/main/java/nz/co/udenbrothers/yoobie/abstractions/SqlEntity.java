package nz.co.udenbrothers.yoobie.abstractions;

import android.content.ContentValues;
import android.util.Log;

import com.google.gson.Gson;

import java.lang.reflect.Field;

import nz.co.udenbrothers.yoobie.App;
import nz.co.udenbrothers.yoobie.tools.sqlUtils.SqlAccess;

public abstract class SqlEntity {

    private long sqlEntityRefId = -1;

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public void save(){

        String table = this.getClass().getSimpleName();
        SqlAccess sql = App.getSqlDatebase();
        ContentValues cv = new ContentValues();
        StringBuilder CREATE_TABLE_NEW = new StringBuilder("CREATE TABLE IF NOT EXISTS " + table + " (sqlEntityRefId integer primary key, ");

        Class<?> thisClass;
        try {
            thisClass = Class.forName(this.getClass().getName());
            Field[] aClassFields = thisClass.getDeclaredFields();
            for(Field f : aClassFields){
                if(!f.isSynthetic()){
                    if (f.get(this) instanceof Integer) {
                        CREATE_TABLE_NEW.append(f.getName()).append(" integer, ");
                        cv.put(f.getName(), (Integer) f.get(this));
                    } else if (f.get(this) instanceof String) {
                        CREATE_TABLE_NEW.append(f.getName()).append(" text, ");
                        cv.put(f.getName(), (String) f.get(this));
                    } else if (f.get(this) == null) {
                        CREATE_TABLE_NEW.append(f.getName()).append(" text, ");
                        cv.putNull(f.getName());
                    } else if (f.get(this) instanceof Long) {
                        CREATE_TABLE_NEW.append(f.getName()).append(" integer, ");
                        cv.put(f.getName(), (Long) f.get(this));
                    } else {
                        CREATE_TABLE_NEW.append(f.getName()).append(" real, ");
                        cv.put(f.getName(), (Double) f.get(this));
                    }
                }
            }

        } catch (Exception e) {
            Log.e("Class reflection Error",e+"");
            return;
        }

        CREATE_TABLE_NEW = new StringBuilder(CREATE_TABLE_NEW.substring(0, CREATE_TABLE_NEW.length() - 2));
        CREATE_TABLE_NEW.append(")");

        sql.createTable(CREATE_TABLE_NEW.toString());

        if(sqlEntityRefId > 0){
            sql.update(table, cv, "sqlEntityRefId", sqlEntityRefId+"");
        }
        else {
            this.sqlEntityRefId = sql.add(table, cv);
        }
        cv.clear();
    }

    public void delete(){
        SqlAccess sql = App.getSqlDatebase();
        String table = this.getClass().getSimpleName();
        sql.delete(table, "sqlEntityRefId", sqlEntityRefId+"");
    }
}
