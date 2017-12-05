package dao.impl;

import dao.BaseDao;
import dao.MessageDao;
import model.Message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MessageDaoImpl extends BaseDao implements MessageDao {
    @Override
    public Boolean sendMessage(String token, String username, String messageText, Long date) {
        String query = "" +
                " INSERT INTO \n" +
                " `db`.`messages` (`from_id`, `to_id`, `text`, `date`) \n" +
                " VALUES \n" +
                " ((SELECT `db`.`users`.`id` FROM `db`.`users` WHERE `db`.`users`.`token` = ?), " +
                " (SELECT `users`.`id` FROM `db`.`users` WHERE `db`.`users`.`username` = ? ), ?, ?); \n";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, username);
            statement.setString(3, messageText);
            statement.setLong(4, date);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Boolean deleteMessage(String token, Integer messageId) {
        String query = "" +
                "DELETE FROM `db`.`messages` WHERE `id` = ? \n" +
                "AND \n" +
                "`messages`.`from_id` = (SELECT id FROM `db`.`users` WHERE `users`.`token` = ?);";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, messageId);
            statement.setString(2, token);
            int count = statement.executeUpdate();
            connection.commit();
            return count > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public List<Message> getMessages(String token, String username) {
        String query = "" +
                " SELECT \n" +
                " `db`.messages.id,\n" +
                " `db`.f.name as from_name, \n" +
                " `db`.t.name as to_name, \n" +
                " `db`.messages.text,\n" +
                " `db`.messages.date\n" +
                " FROM \n" +
                " `db`.messages \n" +
                " LEFT JOIN \n" +
                " `db`.users as f ON messages.from_id = f.id\n" +
                " LEFT JOIN \n" +
                " `db`.users as t ON messages.to_id = t.id\n" +
                " \n" +
                " WHERE \n" +
                " (from_id = (SELECT id FROM `db`.`users` WHERE users.token = ? ) AND to_id = (SELECT id FROM `db`.`users` WHERE `db`.`users`.`username` = ? )) \n" +
                " OR \n" +
                " (from_id = (SELECT id FROM `db`.`users` WHERE `db`.`users`.`username` = ? ) AND to_id = (SELECT id FROM `db`.`users` WHERE users.token = ? ));";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, token);
            statement.setString(2, username);
            statement.setString(3, username);
            statement.setString(4, token);

            return map(statement.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Message> map(ResultSet resultSet) {
        List<Message> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Message msg = new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("from_name"),
                        resultSet.getString("to_name"),
                        resultSet.getString("text"),
                        resultSet.getLong("date")
                );
                result.add(msg);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
