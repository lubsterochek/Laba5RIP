package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.DragonCharacter;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Класс для удаления из коллекции всех драконов, чей характер эквивалентен заданному
 */
public class RemoveByCharacterCommand extends Command {;
    /** Коллекция драконов */
    private final DragonCollection collection;

    /**
     * Конструктор команды
     * @param collection
     */
    public RemoveByCharacterCommand(DragonCollection collection) {
        super("remove_all_by_character");
        this.collection = collection;
    }

    /**
     * Удалить всех драконов с заданным характером
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        if (collection.getDragons().isEmpty()) {
            throw new CommandException("Коллекция пуста! Попробуйте другую команду");
        }

        Scanner scanner = new Scanner(stdin);

        DragonCharacter finalCharacter;
        while (true) {
            try {
                stdout.print("Введите характер дракона (CHAOTIC, CHAOTIC_EVIL, FICKLE): ");
                finalCharacter = DragonCharacter.valueOf(scanner.next().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Ошибка: Неверный ввод. Попробуйте снова.");
            }
        }

        DragonCharacter finalCharacter1 = finalCharacter;
        boolean removed = collection.getDragons().removeIf(dragon -> dragon.getCharacter() == finalCharacter1);

        if (removed) {
            stdout.println("Драконы с характером " + finalCharacter + " успешно удалены.");
        } else {
            throw new CommandException("Драконов с характером " + finalCharacter + " не найдено.");
        }
    }
    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "remove all by character";
    }
}
