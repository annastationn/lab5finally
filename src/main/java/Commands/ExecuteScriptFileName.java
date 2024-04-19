package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class ExecuteScriptFileName implements Command {
    private CommandHandler commandHandler;
    public ExecuteScriptFileName (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("execute_script", this);
    }
    @Override
    public void execute(String arguments) {
        commandHandler.executeScript(arguments);
    }
}
