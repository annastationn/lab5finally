package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

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
