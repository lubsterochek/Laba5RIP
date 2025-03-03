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

/**
 * Класс для команды, обновляющей информацию о драконе по id
 */
public class UpdateIdCommand extends Command {
    /** Поле коллекции */
    private final DragonCollection collection;

    /**
     * Конструктор
     * @param collection
     */
    public UpdateIdCommand(DragonCollection collection) {
        super("update id");
        this.collection = collection;
    }

    /**
     * Метод, который ищет по айди дракона, удаляет старого и задает новые поля, но id остается
     * @param env
     * @param stdin
     * @param stdout
     * @throws CommandException
     */
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

        boolean removed = collection.getDragons().removeIf(dragon -> dragon.getId() == id);

        if (!removed) {
            throw new CommandException("Ошибка: Дракон с ID " + id + " не найден.");
        }
        IdGen.releaseId(id);

        stdout.println("Дракон найден. Введите новые данные: ");
        Dragon newDragon = FormDragons.createDragon(scanner);


        collection.getDragons().add(newDragon);

        stdout.println("Дракон успешно обновлен!");
    }

    /**
     * Краткое описание
     * @return
     */
    @Override
    public String getHelp() {
        return "update elements of Dragon on Id";
    }

}
