package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.csvReaderWriter.CsvWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс, сохраняющий в csv
 */
public class SaveCommand extends Command {
    /** Поле коллекции */
    private final DragonCollection dc;

    /**
     * Конструктор
     * @param dragonCollection
     */
    public SaveCommand(DragonCollection dragonCollection) {
        super("save");
        this.dc = dragonCollection;
    }

    /**
     * Сохраняет в файл, используя метод из CSVWriter
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        try {
            CsvWriter.saveToCSV(dc);
            System.out.println("Дракон успешно записан в файл!");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении в CSV: " + e.getMessage());
        }
    }

    /**
     * Краткое описание команды
     * @return
     */
    @Override
    public String getHelp() {
        return "save in file";
    }
}
