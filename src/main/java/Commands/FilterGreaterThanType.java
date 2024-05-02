package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

public class FilterGreaterThanType implements Command {
    private CommandHandler commandHandler;
    public FilterGreaterThanType(CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("filter_greater_than_type", this);
    }
    @Override
    public void execute(String arguments) {
        commandHandler.filterGreaterThanType(arguments);
    }
}
