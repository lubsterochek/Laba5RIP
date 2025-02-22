package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;

import java.io.InputStream;
import java.io.PrintStream;

public class TestCommand extends Command {
    public TestCommand() {
        super("test");
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout){
        stdout.println("Test command");
    }

    @Override
    public String getHelp(){
        return "This is a test";
    }


}
