package commands.impl;

import commands.Command;
import commands.exceptions.InputException;
import model.RegistrationBlank;
import services.UserService;

import java.util.Scanner;

public class UserRegisterCommand implements Command {

    private final UserService userService;

    public UserRegisterCommand(UserService userService) {
        this.userService = userService;
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
            System.out.println("<<< REGISTRATION >>>");

            String username = getInput(scanner, "Username");
            String password = getInput(scanner, "Password");
            String name = getInput(scanner, "Real name");

            RegistrationBlank registrationBlank = new RegistrationBlank(username, password, name);

            Boolean result = userService.register(registrationBlank);

            if (result)
                System.out.println("Successfully registered");
            else
                System.out.println("Registration failed");
        }
        catch (InputException e){
            System.out.println(e.getMessage());
        }
    }
}