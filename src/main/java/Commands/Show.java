package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class Show implements Command {
    private CommandHandler commandHandler;
    public Show (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("show", this);
    }
    @Override
    public void execute(String arguments) {
        commandHandler.show(arguments);
    }
}
