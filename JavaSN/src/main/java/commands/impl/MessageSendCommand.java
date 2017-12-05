package commands.impl;

import commands.Command;
import services.MessageService;
import utils.TokenStorage;

import java.util.Scanner;

public class MessageSendCommand implements Command {
    private final MessageService messageService;

    public MessageSendCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in, "utf-8");
        System.out.println("<<< SEND MESSAGE >>>");
        System.out.println("Enter receiver's username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your message: ");
        String messageText = scanner.nextLine();

        Boolean result = messageService.sendMessage(TokenStorage.get(), username, messageText);

        if (result)
            System.out.println("Message has been sent");
        else
            System.out.println("Error: message can not be send");

    }
}
