package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

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
