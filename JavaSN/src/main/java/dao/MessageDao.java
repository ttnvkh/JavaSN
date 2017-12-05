package dao;

import model.Message;

import java.util.List;

public interface MessageDao {
    Boolean sendMessage(String token, String username, String messageText, Long date);
    Boolean deleteMessage(String token, Integer messageId);
    List<Message> getMessages(String token, String username);
}
