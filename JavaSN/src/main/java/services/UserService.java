package services;

import model.AuthorizationBlank;
import model.RegistrationBlank;

public interface UserService extends Service {
    String authorize(AuthorizationBlank blank);
    Boolean register(RegistrationBlank blank);
}