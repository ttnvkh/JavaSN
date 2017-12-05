package commands.impl;

import commands.Command;
import commands.exceptions.InputException;
import model.AuthorizationBlank;
import services.UserService;
import utils.TokenStorage;

import java.util.Scanner;


public class UserAuthorizeCommand implements Command {

    private final UserService service;

    public UserAuthorizeCommand(UserService service) {
        this.service = service;
    }

    private String getInput(Scanner scanner, String type) throws InputException {
        System.out.print("Enter your " + type + ": ");
        String line = scanner.nextLine();
        if (!lineValidator(line)) {
            throw new InputException(type + " is not valid");
        }
        return line;
    }

    private boolean lineValidator(String line) {
        return (line.length() > 2);
    }

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in, "utf-8");
            System.out.println("<<< AUTHORIZATION >>>");
            String username = getInput(scanner, "Username");
            String password = getInput(scanner, "Password");
            AuthorizationBlank blank = new AuthorizationBlank(username, password);

            String result = service.authorize(blank);

            if (result.equals("error"))
                System.out.println("Username/Password mismatch");
            else {
                TokenStorage.store(result);
                System.out.println("Logged in successfully.");
            }
        }
        catch (InputException e){
            System.out.println(e.getMessage());
        }
    }
}
