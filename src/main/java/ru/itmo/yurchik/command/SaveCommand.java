package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.csvReaderWriter.CsvWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class SaveCommand extends Command {
    private final DragonCollection dc;
    public SaveCommand(DragonCollection dragonCollection) {
        super("save");
        this.dc = dragonCollection;
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        try {
            // ✅ Сохраняем в CSV
            CsvWriter.saveToCSV(dc);
            System.out.println("Дракон успешно записан в файл!");
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении в CSV: " + e.getMessage());
        }
    }

    @Override
    public String getHelp() {
        return "save in file";
    }
}
