package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

import java.util.LinkedHashMap;
public class Exit implements Command {
    private LinkedHashMap<String, Command> commandMap;
    private CommandHandler commandHandler;
    public Exit (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("exit", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.exit(arguments);
    }
}
