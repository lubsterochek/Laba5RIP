package ru.itmo.yurchik.command;

import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.csvReaderWriter.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Класс для выполнения команда из выбранного файла
 */
public class ExecuteScriptCommand extends Command {
    /** Файлы, которые уже были запущены командой execute_script */
    private static ArrayList<String> usedFiles = new ArrayList<>() ;
    /**
     * Конструктор команды
     */
    public ExecuteScriptCommand() {
        super("execute_script");
    }

    /**
     * Выполнить команду (использовать скрипт)
     *
     * @param env
     * @param stdin
     * @param stdout
     * @param comArgs
     * @throws CommandException
     */
    @Override
    public void execute(Environment env, InputStream stdin, PrintStream stdout, String[] comArgs) throws CommandException {
        String fileName;
        try {
            fileName = comArgs[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("У команды execute_script отсутствует название скрипта!");
            return;
        }

        if (usedFiles.contains(fileName)) {
            stdout.println("Файл попытался запустить " + fileName +  ". Пропускаем эту строку");
            return;
        }

        //СДЕЛАТЬ В РИДЕРЕ НЕ ЧЕРЕЗ ПЕРЕМЕННУЮ ОКРЖУЕНИЯ А ЧЕРНЗ ФАЙЛНЕЙМ
        //Осталось обработать ситуацию, когда нету файлика
        CsvReader reader = new CsvReader();
        List<String[]> comAndArgs = reader.readComAndArgsFromFile(fileName);

        if (comAndArgs.isEmpty()) {
            System.err.println("Ошибка: файл не содержит команд или не может быть прочитан.");
            return;
        }

        usedFiles.add(fileName);

        HashMap<String, Command> mapOfCommands = env.getStringCommandHashMap();

        //Отсюда закончить (Вроде норм, только нижний коммент доделать)
        for (String[] aLine : comAndArgs) {
            String comName = aLine[0];
            String[] args = new String[aLine.length - 1];
            System.arraycopy(aLine, 1, args, 0, args.length);
            if (mapOfCommands.containsKey(comName)) {
                Command command = mapOfCommands.get(comName);
                try {
                    command.execute(env, System.in, System.out, args);
                }
                catch (CommandException e) {
                    //Оставить только e.getMessage(), но прописать у всех команд в CommandException "Ошибка команды ..."
                    System.err.println("Ошибка при выполнении команды: " + e.getMessage());
                }
            } else {
                System.err.println("В файле содержится неизвестная команда: " + comName );
            }
        }

        usedFiles.clear();
    }

    /**
     * Метод для получения описания команды
     * @return
     */
    @Override
    public String getHelp() {
        return "consider and execute the script from the specified file. The script contains commands in the same form in which they are introduced by the user in interactive mode";
    }
}
