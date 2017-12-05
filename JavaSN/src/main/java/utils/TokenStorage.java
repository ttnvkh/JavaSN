package utils;

public class TokenStorage {
    private static String token;
    public static void store(String newToken){
        token = newToken;
    }

    public static String get(){
        return token;
    }
}
