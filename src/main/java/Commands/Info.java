package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class Info implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public Info (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("info", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.info(arguments);
    }
}
