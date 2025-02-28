package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

public class InfoCommand extends Command {
    private final DragonCollection collection;
    public InfoCommand(DragonCollection collection) {
        super("info");
        this.collection = collection;
    }
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
            System.out.println("Информация о коллекции:");
            System.out.println("Тип коллекции: " + collection.getDragons().getClass().getSimpleName());
            System.out.println("Дата инициализации: " + collection.getInitializationTime());
            System.out.println("Количество элементов: " + collection.getDragons().size());
    }

    @Override
    public String getHelp() {
        return "Info about collection";
    }
}
