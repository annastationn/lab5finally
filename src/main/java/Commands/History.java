package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class History implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public History (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("history", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.history(arguments);
    }
}
