package main.java.Commands;

import main.java.CommandHandler;
import main.java.ConsoleApp;

public class PrintUniqueAnnualTurnover implements Command {
    private CommandHandler commandHandler = new CommandHandler();
    public PrintUniqueAnnualTurnover (CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
        ConsoleApp.commandList.put("print_unique_annual_turnover", this);
    }
    @Override
    public void execute(String arguments) {
        commandHandler.printUniqueAnnualTurnover(arguments);
    }
}
