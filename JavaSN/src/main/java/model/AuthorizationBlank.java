package model;


import java.util.Random;

public class AuthorizationBlank {
    private String username;
    private String password;

    public AuthorizationBlank(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String createToken() {
        return String.valueOf(new Random().nextInt());
    }
}
