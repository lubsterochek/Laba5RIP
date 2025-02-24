package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

public class ShowCommand extends Command {
    private final DragonCollection dragonCollection;
    public ShowCommand(DragonCollection dragonCollection) {
        super("show");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        dragonCollection.getDragons().forEach(System.out::println);
    }

    @Override
    public String getHelp() {
        return "show elements of collection Dragons";
    }
}
