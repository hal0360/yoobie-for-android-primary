package nz.co.udenbrothers.yoobie.serverObjects;

public class ProfileToken {
    public String firstName;
    public String lastName;
    public int gender;
    public String dateOfBirth;
    public String phoneMobile;
    public String phoneOther;

    public ProfileToken(){
        firstName = "N/A";
        lastName = "N/A";
        gender = 1;
        dateOfBirth = "N/A";
        phoneMobile = "N/A";
        phoneOther = "00000000";
    }
}
