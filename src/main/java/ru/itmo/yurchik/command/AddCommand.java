package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.time.ZonedDateTime;
import java.util.Scanner;

public class AddCommand extends Command {
    private final DragonCollection dragonCollection;
    public AddCommand(DragonCollection dragonCollection) {
        super("add");
        this.dragonCollection = dragonCollection;
    }
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        Scanner scanner = new Scanner(stdin);
        Dragon newDragon = FormDragons.createDragon(scanner); // Предполагается, что метод сам обрабатывает ошибки
        dragonCollection.addDragon(newDragon);
        stdout.println("Дракон успешно добавлен: " + newDragon);
    }

    @Override
    public String getHelp() {
        return "add new Dragon";
    }
}
