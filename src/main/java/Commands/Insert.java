package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class Insert implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public Insert(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("insert", this);
    }
    @Override
    public void execute(String arguments) {
        commandHandler.insert(arguments);
    }
}
