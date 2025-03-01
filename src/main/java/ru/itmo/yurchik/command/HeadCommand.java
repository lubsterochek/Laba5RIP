package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

public class HeadCommand extends Command {
    private DragonCollection dragons;

    public HeadCommand(DragonCollection dragons) {
        super("head");
        this.dragons = dragons;
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        System.out.printf("Первый элемент коллекции: %1$s", dragons.getDragons().getFirst());
    }

    @Override
    public String getHelp() {
        return "bring the first element of the dragon collection";
    }
}
