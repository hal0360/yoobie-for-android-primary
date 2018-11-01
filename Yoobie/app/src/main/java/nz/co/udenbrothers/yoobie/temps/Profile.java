package nz.co.udenbrothers.yoobie.temps;

import nz.co.udenbrothers.yoobie.App;

public class Profile {

    public static String pass(){
        return App.getStr("pass", "N/A");
    }

    public static void pass(String n){
        App.putStr("pass", n);
    }

    public static String name(){
        return App.getStr("name", "N/A");
    }

    public static void name(String n){
        App.putStr("name", n);
    }

    public static String gender(){
        return App.getStr("gender", "N/A");
    }

    public static void gender(String n){
        App.putStr("gender", n);
    }

    public static String dateOfBirth(){
        return App.getStr("dateOfBirth", "N/A");
    }

    public static void dateOfBirth(String n){
        App.putStr("dateOfBirth", n);
    }

    public static String mail(){
        return App.getStr("mail", "N/A");
    }

    public static void mail(String n){
        App.putStr("mail", n);
    }

    public static String phone(){
        return App.getStr("phone", "N/A");
    }

    public static void phone(String n){
        App.putStr("phone", n);
    }

    public static String location(){
        return App.getStr("location", "N/A");
    }

    public static void location(String n){
        App.putStr("location", n);
    }

    public static String token(){
        return App.getStr("token", null);
    }

    public static void token(String n){
        App.putStr("token", n);
    }

    public static String userID(){
        return App.getStr("userID", null);
    }

    public static void userID(String n){
        App.putStr("userID", n);
    }

    public static String lastAdsId(){
        return App.getStr("lastAdsId", null);
    }

    public static void lastAdsId(String n){
        App.putStr("lastAdsId", n);
    }

    public static void logout(){
        token(null);
        userID(null);
    }
}
