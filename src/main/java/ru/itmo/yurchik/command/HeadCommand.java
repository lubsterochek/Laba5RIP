package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс для вывода первого дракона из коллекции
 */
public class HeadCommand extends Command {
    /** Коллекция дракона */
    private DragonCollection dragons;

    /**
     * Конструктор команды
     * @param dragons
     */
    public HeadCommand(DragonCollection dragons) {
        super("head");
        this.dragons = dragons;
    }

    /**
     * Вывести первого дракона
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        if (dragons.getDragons().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }
        System.out.println("Первый элемент коллекции: %1$s" + dragons.getDragons().getFirst());
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "bring the first element of the dragon collection";
    }
}
