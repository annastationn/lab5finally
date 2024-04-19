package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class RemoveKeyNull implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public RemoveKeyNull (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("remove_key", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.removeKey(arguments);
    }
}
