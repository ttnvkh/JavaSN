package commands.impl;

import commands.Command;
import services.MessageService;
import utils.TokenStorage;

import java.util.Scanner;

public class MessageDeleteCommand implements Command {

    private final MessageService messageService;

    public MessageDeleteCommand(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in, "utf-8");
            System.out.println("<<< DELETE MESSAGE >>>");
            System.out.print("Enter message ID: ");
            Integer msgID = scanner.nextInt();

            Boolean result = messageService.deleteMessage(TokenStorage.get(), msgID);

            if (result) {
                System.out.println("Message has been deleted");
            } else {
                System.out.println("Error: can't delete message");
            }
        } catch (Exception e) {
            System.out.println("Error: wrong message ID");
        }
    }
}
