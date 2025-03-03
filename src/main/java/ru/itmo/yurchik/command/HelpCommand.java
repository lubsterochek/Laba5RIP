package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

/**
 * Класс для вывода названий всех команд
 */
public class HelpCommand extends Command {
    /**
     * Конструктор команды
     */
    public HelpCommand() {
        super("help");
    }

    /**
     * Представить все команды
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) {
        HashMap<String, Command> stringCommandHashMap = env.getStringCommandHashMap();
        stringCommandHashMap.forEach((key, value) -> {
            stdout.println(key + ": " + value.getHelp());
        });
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "This is a help command";
    }
}
