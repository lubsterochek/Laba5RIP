package ru.itmo.yurchik.command.exception;

/**
 * Класс-ошибка для всех команд. Нужен, чтобы обрабатывать ошибки пользовательского ввода
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }
}
