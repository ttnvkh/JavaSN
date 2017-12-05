package dao;

import model.AuthorizationBlank;
import model.RegistrationBlank;

public interface UserDao {
    Boolean createUser(RegistrationBlank blank);
    String createToken(AuthorizationBlank blank);
}
