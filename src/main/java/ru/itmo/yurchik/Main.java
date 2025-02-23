package ru.itmo.yurchik;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.*;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //new Dragon(1234124, "xyesos", new Coordinates(), 1, 10L, Color.BLUE, DragonType.AIR, DragonCharacter.CHAOTIC, new DragonCave());

        //для контроля потоков
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        Scanner in = new Scanner(stdin);
        stdout.print("Введите команду: ");

        DragonCollection dc = new DragonCollection();

        HashMap<String, Command> map = new HashMap<>();

        TestCommand testCommand = new TestCommand();
        map.put(testCommand.getName(), testCommand);
        //"test" -> testCommand
        HelloCommand helloCommand = new HelloCommand();
        map.put(helloCommand.getName(), helloCommand);
        //"hello" -> helloCommand
        ExitCommand exitCommand = new ExitCommand();
        map.put(exitCommand.getName(), exitCommand);
        HelpCommand helpCommand = new HelpCommand();
        map.put(helpCommand.getName(), helpCommand);
        RemoveFirst removeFirst = new RemoveFirst(dc);
        map.put(removeFirst.getName(), removeFirst);

        Environment environment = new Environment(map);

        while (in.hasNext()) {
            String line = in.nextLine();
            if (map.keySet().contains(line)) {
                Command command = map.get(line);
                try {
                    command.execute(environment, System.in, System.out);
                }
                catch (CommandException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("Unknown command: " + line + "\n Введите <help> для списка команд");
            }
        }
    }
}