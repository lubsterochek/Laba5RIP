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
        try {
            stdout.println("Введите имя дракона: ");
            String name = scanner.nextLine();

            stdout.println("Введите координаты (x, y): ");
            float x = Float.parseFloat(scanner.next());
            double y = Double.parseDouble(scanner.next());

            stdout.println("Введите возраст дракона: ");
            Long age = Long.parseLong(scanner.next());

            stdout.println("Введите цвет (BLUE, YELLOW, WHITE, BROWN): ");
            Color color = Color.valueOf(scanner.next().toUpperCase());

            stdout.println("Введите тип дракона (WATER, UNDERGROUND, AIR): ");
            DragonType type = DragonType.valueOf(scanner.next().toUpperCase());

            stdout.println("Введите характер (CHAOTIC, CHAOTIC_EVIL, FICKLE): ");
            DragonCharacter character = DragonCharacter.valueOf(scanner.next().toUpperCase());

            stdout.println("Введите глубину пещеры и количество сокровищ: ");
            double depth = Double.parseDouble(scanner.next());
            Float treasures = Float.parseFloat(scanner.next());

            // 🔹 Создаем нового дракона
            Dragon newDragon = new Dragon(name, new Coordinates(x, y), ZonedDateTime.now(), age, color, type, character, new DragonCave(depth, treasures));

            // 🔹 Добавляем дракона в коллекцию
            dragonCollection.addDragon(newDragon);
            stdout.println("✅ Дракон успешно добавлен: " + newDragon);

        } catch (Exception e) {
            stdout.println(" Ошибка при вводе данных! Попробуйте еще раз.");
        }
    }

    @Override
    public String getHelp() {
        return "add new Dragon";
    }
}
