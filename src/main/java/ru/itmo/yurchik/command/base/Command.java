package ru.itmo.yurchik.command.base;

import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class Command{
    private final String name;

    protected Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException;

    public abstract String getHelp();
}
