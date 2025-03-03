package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс команда, описывающая коллекцию
 */
public class ShowCommand extends Command {
    /** Поле коллекции */
    private final DragonCollection dragonCollection;

    /**
     * Конструктор
     * @param dragonCollection
     */
    public ShowCommand(DragonCollection dragonCollection) {
        super("show");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Метод для показа содержания коллекции
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
        dragonCollection.getDragons().forEach(System.out::println);
    }

    /**
     * Краткое описание команды
     * @return
     */
    @Override
    public String getHelp() {
        return "show elements of collection Dragons";
    }
}
