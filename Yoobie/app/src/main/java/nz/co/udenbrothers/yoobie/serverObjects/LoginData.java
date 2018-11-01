package nz.co.udenbrothers.yoobie.serverObjects;

import nz.co.udenbrothers.yoobie.abstractions.ServObj;

/**
 * Created by user on 18/01/2018.
 */

public class LoginData extends ServObj{
    private String email;
    private String password;
    private String deviceToken;
    private String deviceModel;

    public LoginData(String em, String pass, String dm){
        email = em;
        password = pass;
        deviceToken = "N/A";
        deviceModel = dm;
    }
}
