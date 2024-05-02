package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

public class Save implements Command {
    private CommandHandler commandHandler;
    public Save (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("save", this);
    }
    @Override
    public void execute(String arguments) {
        commandHandler.save(arguments);
    }
}
