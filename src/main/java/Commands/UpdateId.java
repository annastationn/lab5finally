package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class UpdateId implements Command {
    private CommandHandler commandHandler;
    public UpdateId (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("update", this);
    }
    @Override
    public void execute(String arguments) {
         commandHandler.updateById(arguments);
    }
}
