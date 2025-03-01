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
        //try {

            Dragon nd = new Dragon("ddaad", new Coordinates(1, 1), ZonedDateTime.now(), 18L, Color.BROWN, DragonType.UNDERGROUND, DragonCharacter.CHAOTIC, new DragonCave(1, 0F));

            stdout.println("Введите имя дракона: ");
            String name = scanner.nextLine();
            nd.setName(name);

            stdout.println("Введите координаты (x, y): ");
            float x = Float.parseFloat(scanner.next());
            double y = Double.parseDouble(scanner.next());
            nd.setCoordinates(new Coordinates(x, y));

            stdout.println("Введите возраст дракона: ");
            Long age = Long.parseLong(scanner.next());
            nd.setAge(age);

            stdout.println("Введите цвет (BLUE, YELLOW, WHITE, BROWN): ");
            Color color = Color.valueOf(scanner.next().toUpperCase());
            nd.setColor(color);

            stdout.println("Введите тип дракона (WATER, UNDERGROUND, AIR): ");
            DragonType type = DragonType.valueOf(scanner.next().toUpperCase());
            nd.setType(type);

            stdout.println("Введите характер (CHAOTIC, CHAOTIC_EVIL, FICKLE): ");
            DragonCharacter character = DragonCharacter.valueOf(scanner.next().toUpperCase());
            nd.setCharacter(character);

            stdout.println("Введите глубину пещеры и количество сокровищ: ");
            double depth = Double.parseDouble(scanner.next());
            Float treasures = Float.parseFloat(scanner.next());
            DragonCave cave = new DragonCave(depth, treasures);
            nd.setCave(cave);



            // 🔹 Создаем нового дракона
            //Dragon newDragon = new Dragon(name, new Coordinates(x, y), ZonedDateTime.now(), age, color, type, character, new DragonCave(depth, treasures));

            // 🔹 Добавляем дракона в коллекцию
            dragonCollection.addDragon(nd);
            stdout.println("✅ Дракон успешно добавлен: " + nd);

        //} catch (Exception e) {
           // stdout.println(" Ошибка при вводе данных! Попробуйте еще раз.");
        //}
    }

    @Override
    public String getHelp() {
        return "add new Dragon";
    }
}
