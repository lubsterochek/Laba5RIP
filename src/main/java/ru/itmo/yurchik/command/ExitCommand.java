package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.Main;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.Scanner;

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
     * @param env
     * @param stdin
     * @param stdout
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) {
        stdout.print("Хорошего дня! ♡ (*^ω^)");
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
