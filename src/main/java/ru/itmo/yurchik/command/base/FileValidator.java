package ru.itmo.yurchik.command.base;

import ru.itmo.yurchik.command.exception.CommandException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {
    public static boolean isFileValid(String envVarName) throws CommandException {
        // Получаем путь из переменной окружения
        String filePath = System.getenv(envVarName);

        // Проверяем, что переменная окружения вообще установлена
        if (filePath == null || filePath.isEmpty()) {
            throw new CommandException ("Ошибка: Переменная окружения " + envVarName + " не установлена!");
        }

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new CommandException ("Ошибка: Файл " + filePath + " не существует!");
        }

        if (!Files.isRegularFile(path)) {
            throw new CommandException("Ошибка: " + filePath + " не является файлом!");
        }

        if (!Files.isReadable(path)) {
            throw new CommandException("Ошибка: Файл " + filePath + " недоступен для чтения!");
        }


        try {
            if (Files.size(path) == 0) {
                throw new CommandException("Ошибка: Файл " + filePath + " пуст!");
            }
        } catch (Exception e) {
            throw new CommandException("Ошибка при проверке размера файла: " + e.getMessage());
        }

        System.out.println("Файл " + filePath + " прошел все проверки!");
        return true;
    }

}
