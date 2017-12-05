package commands.impl;

import commands.Command;
import model.Message;
import services.MessageService;
import utils.TokenStorage;

import java.util.List;
import java.util.Scanner;

public class MessagesGetCommand implements Command {

    private final MessageService messageService;

    public MessagesGetCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in, "utf-8");
        System.out.println("<<< GET MESSAGES >>>");
        System.out.println("Enter receiver's username: ");
        String username = scanner.nextLine();

        List<Message> messageList = messageService.getMessages(TokenStorage.get(), username);

        if (messageList.size() != 0) {
            System.out.println("Message list with " + username + ":");
            for (Message message : messageList) {
                System.out.println(message);
            }
        } else
            System.out.println("Error: can't get messages list");

    }
}
