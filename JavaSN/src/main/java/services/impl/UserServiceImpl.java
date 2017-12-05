package services.impl;

import dao.UserDao;
import model.AuthorizationBlank;
import model.RegistrationBlank;
import services.UserService;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String authorize(AuthorizationBlank blank) {
        return userDao.createToken(blank);
    }

    @Override
    public Boolean register(RegistrationBlank blank) {
        return userDao.createUser(blank);
    }
}
