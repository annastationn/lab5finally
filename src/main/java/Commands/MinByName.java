package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

public class MinByName implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public MinByName (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("min_by_name", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.minByName(arguments);
    }
}
