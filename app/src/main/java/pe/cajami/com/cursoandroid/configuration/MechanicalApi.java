package pe.cajami.com.cursoandroid.configuration;

public class MechanicalApi {

    public static String URL_BASE = "http://192.168.1.9:3000";
    //AndroidNetworking.get("https://mechanical-assistance-cajamisoft.c9users.io/api/user")
    private static String URL_API = "/api";

    private static String getApi() {
        return URL_BASE + URL_API;
    }

    public static String getAutenticat() {
        return URL_BASE + "/autenticate";
    }

    public  static String getRegisterUser(){
        return URL_BASE + "/register";
    }
}
