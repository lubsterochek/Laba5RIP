package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс для вывода информации о коллекции
 */
public class InfoCommand extends Command {
    /** Коллекция драконов */
    private final DragonCollection collection;

    /**
     * Конструктор команды
     * @param collection
     */
    public InfoCommand(DragonCollection collection) {
        super("info");
        this.collection = collection;
    }

    /**
     * Вывести информацию о коллекции
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        if (collection.getDragons().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }
        System.out.println("Информация о коллекции:");
        System.out.println("Тип коллекции: " + collection.getDragons().getClass().getSimpleName());
        System.out.println("Дата инициализации: " + collection.getInitializationTime());
        System.out.println("Количество элементов: " + collection.getDragons().size());
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "Info about collection";
    }
}
