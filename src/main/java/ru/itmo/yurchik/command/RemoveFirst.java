package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.Dragon;
import ru.itmo.yurchik.model.IdGen;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс для удаления первого дракона из коллекции
 */
public class RemoveFirst extends Command {
    /** Коллекция драконов */
    private final DragonCollection dragonCollection;

    /**
     * Конструктор команды
     * @param dragonCollection
     */
    public RemoveFirst(DragonCollection dragonCollection) {
        super("remove_first");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Удалить первого дракона
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        if (!dragonCollection.getDragons().isEmpty()) {
            Dragon firstDragon = dragonCollection.getDragons().getFirst();
            dragonCollection.getDragons().removeFirst();
            IdGen.releaseId(firstDragon.getId());
            System.out.println("Первый дракон удален! Его ID:  " + firstDragon.getId());
        } else {
            throw new CommandException("В коллекции никого нет! Попробуйте другую команду");
        }
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "Remove first element of collection";
    }
}
