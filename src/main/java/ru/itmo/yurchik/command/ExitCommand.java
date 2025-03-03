package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс для прекращения работы программы
 */
public class ExitCommand extends Command {
    /**
     * Конструктор команды
     */
    public ExitCommand() {
        super("exit");
    }

    /**
     * Завершить программу (без сохранения изменений)
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) {
        stdout.print("Хорошего дня! ♡ (*^w^)");
        System.exit(0);
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "exit without save";
    }
}
