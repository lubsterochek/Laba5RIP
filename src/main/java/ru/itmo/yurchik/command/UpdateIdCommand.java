package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.Dragon;
import ru.itmo.yurchik.model.FormDragons;
import ru.itmo.yurchik.model.IdGen;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UpdateIdCommand extends Command {
    private final DragonCollection collection;

    public UpdateIdCommand(DragonCollection collection) {
        super("update id");
        this.collection = collection;
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        Scanner scanner = new Scanner(stdin);

        stdout.print("Введите ID дракона для обновления: ");
        int id;

        try {
            id = Integer.parseInt(scanner.nextLine()); // Читаем ID
        } catch (NumberFormatException e) {
            throw new CommandException("Ошибка: ID должен быть числом.");
        }

        // Проверяем, есть ли дракон с таким ID
        boolean removed = collection.getDragons().removeIf(dragon -> dragon.getId() == id);

        if (!removed) {
            throw new CommandException("Ошибка: Дракон с ID " + id + " не найден.");
        }
        IdGen.releaseId(id);

        stdout.println("Дракон найден. Введите новые данные: ");
        Dragon newDragon = FormDragons.createDragon(scanner); // Создаём нового дракона


        collection.getDragons().add(newDragon); // Добавляем нового дракона в коллекцию

        stdout.println("Дракон успешно обновлен!");
    }

    @Override
    public String getHelp() {
        return "update elements of Dragon on Id";
    }

}
