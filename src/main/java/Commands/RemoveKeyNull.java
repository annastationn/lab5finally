package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

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
