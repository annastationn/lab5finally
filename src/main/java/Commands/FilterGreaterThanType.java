package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

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
