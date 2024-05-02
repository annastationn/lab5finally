package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

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
