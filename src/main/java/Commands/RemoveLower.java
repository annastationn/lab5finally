package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class RemoveLower implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public RemoveLower (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("remove_lower", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.removeLower(arguments);
    }
}
