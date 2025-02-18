package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.Main;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit");
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) {
        System.out.print("Хорошего дня! ♡ (*^ω^)");
        Scanner sc = new Scanner(System.in);
        sc.close();
    }

    @Override
    public String getHelp() {
        return "exit without save";
    }
}
