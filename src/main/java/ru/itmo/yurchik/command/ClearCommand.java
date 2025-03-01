package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

public class ClearCommand extends Command {
    private final DragonCollection dragons;

    public ClearCommand(DragonCollection dragons) {
        super("clear");
        this.dragons = dragons;
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        while(!dragons.getDragons().isEmpty()) {
            dragons.getDragons().removeFirst();
        }
        stdout.println("Коллекция драконов очищена!");
    }

    @Override
    public String getHelp() {
        return "clean the dragon collection";
    }
}
