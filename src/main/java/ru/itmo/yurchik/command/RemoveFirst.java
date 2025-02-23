package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class RemoveFirst extends Command {
    private final DragonCollection dragonCollection;
    public RemoveFirst(DragonCollection dragonCollection) {
        super("remove_first");
        this.dragonCollection = dragonCollection;
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) {
        dragonCollection.getDragons().removeFirst();
    }

    @Override
    public String getHelp() {
        return "Remove first element of collection";
    }
}
