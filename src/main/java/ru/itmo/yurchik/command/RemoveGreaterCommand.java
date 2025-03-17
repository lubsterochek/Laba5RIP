package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.Dragon;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Класс для удаления драконов, которые старше заданного дракона
 */
public class RemoveGreaterCommand extends Command {
    /** Коллекция драконов */
    private DragonCollection dragonCollection;

    /**
     * Конструктор команды
     * @param dragonCollection
     */
    public RemoveGreaterCommand(DragonCollection dragonCollection) {
        super("remove_greater");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Удалить драконов, старше заданных
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        if (dragonCollection.getDragons().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }

        Dragon referenceDragon = null;

        String name;

        try {
            name = comArgs[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandException("Вы не ввели имя дракона");
        }

        for (Dragon d : dragonCollection.getDragons()) {
            if (d.getName().equals(name)) {
                referenceDragon = d;
                break;
            }
        }

        if (referenceDragon == null) {
            throw new CommandException("Дракон с таким именем не найден! Попробуйте другую команду");
        }

        int initialSize = dragonCollection.getDragons().size();

        Iterator<Dragon> iterator = dragonCollection.getDragons().iterator();
        while (iterator.hasNext()) {
            Dragon dragon = iterator.next();
            if (dragon.getAge() > referenceDragon.getAge()) {
                iterator.remove();
            }
        }

        int removedCount = initialSize - dragonCollection.getDragons().size();
        System.out.println("Удалено элементов: " + removedCount);
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "delete all dragons from the collection, which are older than a given";
    }
}
