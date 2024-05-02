package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

public class Clear implements Command {
    private CommandHandler commandHandler;
    public Clear (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("clear", this);
    }

    @Override
    public void execute(String arguments) {
         commandHandler.clear(arguments);
    }
}
