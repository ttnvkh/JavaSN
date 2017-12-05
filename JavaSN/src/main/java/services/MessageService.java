package services;

import model.Message;

import java.util.List;


public interface MessageService extends Service {
    Boolean sendMessage(String token, String username, String messageText);
    Boolean deleteMessage(String token, Integer messageId);
    List<Message> getMessages(String token, String username);
}