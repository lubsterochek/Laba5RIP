package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class HelloCommand extends Command {
    public HelloCommand() {
        super("hello");
    }


    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) {
        stdout.println("Hello World!");
    }

    @Override
    public String getHelp() {
        return "This is a command hello.";
    }
}
