package services.impl;

import dao.MessageDao;
import model.Message;
import services.MessageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MessageServiceImpl implements MessageService {
    private final MessageDao messageDao;

    public MessageServiceImpl(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Override
    public Boolean sendMessage(String token, String username, String messageText) {
        return messageDao.sendMessage(token, username, messageText, new Date().getTime());
    }

    @Override
    public Boolean deleteMessage(String token, Integer messageId) {
        return messageDao.deleteMessage(token, messageId);
    }

    @Override
    public List<Message> getMessages(String token, String username) {
        return messageDao.getMessages(token, username);
    }
}
