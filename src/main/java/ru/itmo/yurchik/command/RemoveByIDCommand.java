package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.Dragon;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Класс для удаления дракона по его ID
 */
public class RemoveByIDCommand extends Command {
    /** Коллекция драконов */
    private DragonCollection dragonCollection;

    /**
     * Конструктор команды
     * @param dragonCollection
     */
    public RemoveByIDCommand(DragonCollection dragonCollection) {
        super("remove_by_id");
        this.dragonCollection = dragonCollection;
    }

    /**
     * Удалить дракона по ID
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        if (dragonCollection.getDragons().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }

        stdout.println("Введите ID дракона, которого хотите удалить: ");
        Scanner scanner = new Scanner(stdin);
        int id;

        try {
            id = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new CommandException("Ошибка: ID должен быть числом. Попробуйте другую команду");
        }

        Iterator<Dragon> iterator = dragonCollection.getDragons().iterator();
        boolean removed = false;

        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Дракон с ID " + id + " успешно удалён.");
        } else {
            System.out.println("Дракон с таким ID не найден. Попробуйте другую команду ");
        }
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "delete the element from the collection according to its ID";
    }
}
