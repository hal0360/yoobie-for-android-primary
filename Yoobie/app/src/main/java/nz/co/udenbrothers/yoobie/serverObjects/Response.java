package nz.co.udenbrothers.yoobie.serverObjects;

import nz.co.udenbrothers.yoobie.abstractions.ServObj;

public class Response extends ServObj {

    public String content;
    public int statusCode;

    public Response(String content, int code){
        this.content = content;
        statusCode = code;
    }
}

