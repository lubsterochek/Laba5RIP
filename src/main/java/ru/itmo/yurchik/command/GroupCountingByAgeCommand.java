package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.Dragon;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupCountingByAgeCommand extends Command {
    private DragonCollection dragonCollection;
    public GroupCountingByAgeCommand(DragonCollection dragonCollection) {
        super("group_counting_by_age");
        this.dragonCollection = dragonCollection;
    }
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        if (dragonCollection.getDragons().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }

        Map<Long, Long> ageGroups = dragonCollection.getDragons()
                .stream()
                .collect(Collectors.groupingBy(Dragon::getAge, Collectors.counting()));

        stdout.println("Группировка по возрасту: ");
        ageGroups.forEach((age, count) -> stdout.println("Возраст " + age + ": " + count + " драконов"));
    }

    @Override
    public String getHelp() {
        return "group elements of the collection by the age, derive the number of elements in each group";
    }
}
