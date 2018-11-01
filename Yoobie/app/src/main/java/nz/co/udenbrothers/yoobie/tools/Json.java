package nz.co.udenbrothers.yoobie.tools;

import com.google.gson.Gson;

import java.util.List;

import nz.co.udenbrothers.yoobie.tools.sqlUtils.ListOfJson;



public class Json {

    public static <T> List<T> fromList(String js, Class<T> tClass){
        Gson gson = new Gson();
        return gson.fromJson(js, new ListOfJson<>(tClass));
    }

    public static <T> T from(String js, Class<T> tClass){
        Gson gson = new Gson();
        return gson.fromJson(js, tClass);
    }

    public static <T> String to(T t){
        Gson gson = new Gson();
        return gson.toJson(t);
    }

    public static <T> String to(List<T> ts){
        Gson gson = new Gson();
        return gson.toJson(ts);
    }
}
