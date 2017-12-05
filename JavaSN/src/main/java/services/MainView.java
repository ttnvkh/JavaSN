package services;

import commands.Command;
import commands.Invoker;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;


public class MainView {
    private final Invoker invoker;

    public MainView(Invoker invoker) {
        this.invoker = invoker;
    }

    public void showMenu() {
        ClassPathXmlApplicationContext appContext =
                new ClassPathXmlApplicationContext("/application-context.xml");

        Command userRegisterCommand = (Command) appContext.getBean("register");
        Command userAuthorizeCommand = (Command) appContext.getBean("authorize");
        Command messageSendCommand = (Command) appContext.getBean("sendMessage");
        Command messageDeleteCommand = (Command) appContext.getBean("deleteMessage");
        Command messagesGetCommand = (Command) appContext.getBean("getMessages");

        try (Scanner scanner = new Scanner(System.in, "UTF-8")) {
            while(true){
                System.out.println("<<< SOCIAL NETWORK >>>");
                System.out.println("Function list: ");
                System.out.println("1 - Authorize");
                System.out.println("2 - Register");
                System.out.println("3 - Send Message");
                System.out.println("4 - Delete Message");
                System.out.println("5 - Get Messages List");
                System.out.println("0 - Exit");

                System.out.print("Enter the command number: ");
                int i = scanner.nextInt();

                switch (i) {
                    case 1:
                        invoker.setCommand(userAuthorizeCommand);
                        invoker.run();
                        break;
                    case 2:
                        invoker.setCommand(userRegisterCommand);
                        invoker.run();
                        break;
                    case 3:
                        invoker.setCommand(messageSendCommand);
                        invoker.run();
                        break;
                    case 4:
                        invoker.setCommand(messageDeleteCommand);
                        invoker.run();
                        break;
                    case 5:
                        invoker.setCommand(messagesGetCommand);
                        invoker.run();
                        break;

                    case 0:
                        System.exit(0);
                    default:
                        throw new IllegalArgumentException("");

                }
            }

        }
    }
}
