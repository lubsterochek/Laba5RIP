package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс для удаления всех драконов из коллекции
 */
public class ClearCommand extends Command {
    /** Поле коллекции драконов */
    private final DragonCollection dragons;

    /**
     * Конструктор команды
     * @param dragons
     */
    public ClearCommand(DragonCollection dragons) {
        super("clear");
        this.dragons = dragons;
    }

    /**
     * Выполнить команду (очистить коллекцию)
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        dragons.getDragons().clear();
        stdout.println("Коллекция драконов очищена!");
    }

    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "clean the dragon collection";
    }
}
