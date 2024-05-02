package main.java.Commands;

import main.java.Modules.CommandHandler;
import main.java.Modules.ConsoleApp;

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
