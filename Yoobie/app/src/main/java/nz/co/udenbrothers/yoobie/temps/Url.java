package nz.co.udenbrothers.yoobie.temps;


public class Url {
    public static String CHECK_EMAIL(String email){return "http://yoobie-api.azurewebsites.net/registration/email-available?email=" + email;}
    public static String CHECK_PHONE(String number){return "http://yoobie-api.azurewebsites.net/registration/phone-mobile-available?phone=" + number;}
    public static final String SIGN_IN = "http://yoobie-api.azurewebsites.net/login";
    public static final String SIGN_UP = "http://yoobie-api.azurewebsites.net/registration";
    public static final String GET_COUNTRIES = "http://yoobie-api.azurewebsites.net/registration/country";
    public static final String GET_PROFILE = "http://yoobie-api.azurewebsites.net/profile";
    public static String GET_REGION(int countryID){return "http://yoobie-api.azurewebsites.net/registration/country/" + countryID + "/region";}
    public static String POST_STAMPS = "http://yoobie-api.azurewebsites.net/impression";
    public static String GET_IMAGES(String campaignID, String imageID){return "http://yoobie-api.azurewebsites.net/campaign/" + campaignID + "/image/" + imageID;}
    public static final String GET_CAMPAIGN = "http://yoobie-api.azurewebsites.net/campaign";
}
