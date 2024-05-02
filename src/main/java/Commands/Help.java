package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

public class Help implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public Help (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("help", this);
    }
    @Override
    public void execute(String arguments){
         commandHandler.help(arguments);
    }
}
