package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;

public class HelpCommand extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) {
        HashMap<String, Command> stringCommandHashMap = env.getStringCommandHashMap();
        stringCommandHashMap.forEach((key, value) -> {
            stdout.println(key + ": " + value.getHelp());
        });
    }

    @Override
    public String getHelp() {
        return "This is a help command";
    }
}
