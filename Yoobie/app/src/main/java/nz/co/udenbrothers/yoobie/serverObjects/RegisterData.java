package nz.co.udenbrothers.yoobie.serverObjects;

import nz.co.udenbrothers.yoobie.abstractions.ServObj;

public class RegisterData extends ServObj {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String dateOfBirth;
    public int gender;
    public int countryId;
    public int countryRegionId;
    public String deviceToken;
    public String phoneMobile;
    public String deviceModel;
    public String phoneOther;

    public RegisterData(){
        firstName = "N/A";
        lastName = "N/A";
        password = "N/A";
        email = "N/A";
        gender = 0;
        dateOfBirth = "N/A";
        deviceToken = "N/A";
        phoneMobile = "N/A";
        phoneOther = "00000000";
        countryId = 20;
        countryRegionId = 370;
        deviceModel = "N/A";
    }
}
