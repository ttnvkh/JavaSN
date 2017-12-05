package dao.impl;

import dao.BaseDao;
import dao.UserDao;
import model.AuthorizationBlank;
import model.RegistrationBlank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public Boolean createUser(RegistrationBlank blank) {
        String query = "INSERT INTO `db`.`users` (username, name, password) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, blank.getUsername());
            statement.setString(2, blank.getName());
            statement.setString(3, blank.getPassword());
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) { return false; }
    }

    @Override
    public String createToken(AuthorizationBlank blank) {
        String query = "UPDATE `db`.`users` SET `token`= ? " +
                "WHERE (`username`= ? AND `password` = ?);";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            String token = blank.createToken();
            statement.setString(1, token);
            statement.setString(2, blank.getUsername());
            statement.setString(3, blank.getPassword());
            int count = statement.executeUpdate();
            connection.commit();
            return count > 0 ? token : "error";
        } catch (SQLException e) { return "error"; }
    }
}
