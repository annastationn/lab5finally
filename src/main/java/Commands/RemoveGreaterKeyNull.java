package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

public class RemoveGreaterKeyNull implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public RemoveGreaterKeyNull(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("remove_greater_key", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.removeGreaterKey(arguments);
    }
}
