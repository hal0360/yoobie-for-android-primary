package nz.co.udenbrothers.yoobie.abstractions;

import com.google.gson.Gson;

public abstract class ServObj {

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
