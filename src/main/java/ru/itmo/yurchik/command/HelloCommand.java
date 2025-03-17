package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

/**
 * Класс для вывода приветствия
 */
public class HelloCommand extends Command {
    /**
     * Конструктор команды
     */
    public HelloCommand() {
        super("hello");
    }

    /**
     * Вывести: Hello, World!
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) {
        stdout.println("Hello, World!");
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "This is a command hello.";
    }
}
