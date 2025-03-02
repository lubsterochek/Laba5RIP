package ru.itmo.yurchik.command;

import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.csvReaderWriter.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command {
    private DragonCollection dragonCollection;
    public ExecuteScriptCommand(DragonCollection dragonCollection) {
        super("execute_script");
        this.dragonCollection = new DragonCollection();
    }

    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout) throws CommandException {
        stdout.println("Введите название переменной окружения файла, который хотите использовать: ");
        Scanner scanner = new Scanner(stdin);
        String envName = scanner.nextLine();

        CsvReader reader = new CsvReader(envName);
        List<String> commands = reader.readCommandsFromFile();

        if (commands.isEmpty()) {
            stdout.println("Ошибка: файл не содержит команд или не может быть прочитан.");
            return;
        }

        HashMap<String, Command> mapOfCommands = env.getStringCommandHashMap();

        for (String line : commands) {
            line = line.trim();
            if (mapOfCommands.containsKey(line)) {
                Command command = mapOfCommands.get(line);
                try {
                    command.execute(env, System.in, System.out);
                } catch (CommandException e) {
                    System.err.println("Ошибка при выполнении команды: " + e.getMessage());
                }
            } else {
                System.err.println("Неизвестная команда: " + line + "\nВведите <help> для списка команд.");
            }
        }
    }

    @Override
    public String getHelp() {
        return "consider and execute the script from the specified file. The script contains commands in the same form in which they are introduced by the user in interactive mode";
    }
}
