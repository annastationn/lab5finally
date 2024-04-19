package main.java;

import main.java.Commands.*;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ConsoleApp consoleApp = createConsoleApp();
        PromptScanner.setUserScanner(new Scanner(System.in));
        var scanner = PromptScanner.getUserScanner();

        System.out.println("===================================");
        consoleApp.help("");
        System.out.print("> ");

        while (true) {
            try {
                while (scanner.hasNext()) {
                    var command = "";
                    var arguments = "";
                    String[] input = (scanner.nextLine() + " ").trim().split(" ", 2);
                    if (input.length == 2) {
                        arguments = input[1].trim();
                    }
                    command = input[0].trim();

                    if (ConsoleApp.commandList.containsKey(command)) {
                        ConsoleApp.commandList.get(command).execute(arguments);
                        CommandHandler.addCommand(command);
                    } else {
                        System.out.println("Unknown command. Keep trying...");
                    }
                    System.out.print("> ");
                }
            } catch (NoSuchElementException e) {
                System.out.println("Exit...");
                System.out.println(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                System.exit(1);
            }

        }
    }

    private static ConsoleApp createConsoleApp() {
        CommandHandler commandHandler = new CommandHandler();
        return new ConsoleApp(
                new Help(commandHandler),
                new Info(commandHandler),
                new Show(commandHandler),
                new Insert(commandHandler),
                new UpdateId(commandHandler),
                new RemoveKeyNull(commandHandler),
                new Clear(commandHandler),
                new Save(commandHandler),
                new ExecuteScriptFileName(commandHandler),
                new Exit(commandHandler),
                new RemoveGreaterKeyNull(commandHandler),
                new RemoveLower(commandHandler),
                new History(commandHandler),
                new MinByName(commandHandler),
                new PrintUniqueAnnualTurnover(commandHandler),
                new FilterGreaterThanType(commandHandler)
        );
    }

}