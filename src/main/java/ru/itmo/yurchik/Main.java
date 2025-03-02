package ru.itmo.yurchik;

import com.opencsv.CSVReader;
import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.*;
import ru.itmo.yurchik.command.base.Command;
import ru.itmo.yurchik.command.base.Environment;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.csvReaderWriter.CsvReader;
import ru.itmo.yurchik.model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Dragon drug1 = new Dragon( "Yura", new Coordinates(1, 1), ZonedDateTime.now(), 19L, Color.BLUE, DragonType.AIR, DragonCharacter.CHAOTIC_EVIL, new DragonCave(10, 10F));
        //Dragon drug2 = new Dragon("Leva", new Coordinates(2,2 ), ZonedDateTime.now(), 18L, Color.BROWN, DragonType.UNDERGROUND, DragonCharacter.CHAOTIC, new DragonCave(1, 0F));

        DragonCollection dc = new DragonCollection();
        //dc.addDragon(drug1);
        //dc.addDragon(drug2);

        CsvReader reader = new CsvReader("DRAGON_CSV");
        reader.loadDragons(dc);

        //для контроля потоков
        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        Scanner in = new Scanner(stdin);
        stdout.print("Введите команду: ");

        HashMap<String, Command> map = new HashMap<>();

        TestCommand testCommand = new TestCommand();
        map.put(testCommand.getName(), testCommand);
        //"test" -> testCommand
        HelloCommand helloCommand = new HelloCommand();
        map.put(helloCommand.getName(), helloCommand);
        //"hello" -> helloCommand
        ExitCommand exitCommand = new ExitCommand();
        map.put(exitCommand.getName(), exitCommand);

        HelpCommand helpCommand = new HelpCommand();
        map.put(helpCommand.getName(), helpCommand);

        RemoveFirst removeFirst = new RemoveFirst(dc);
        map.put(removeFirst.getName(), removeFirst);

        ShowCommand showCommand = new ShowCommand(dc);
        map.put(showCommand.getName(), showCommand);

        InfoCommand infoCommand = new InfoCommand(dc);
        map.put(infoCommand.getName(), infoCommand);

        SaveCommand saveCommand = new SaveCommand(dc);
        map.put(saveCommand.getName(), saveCommand);

        AddCommand addCommand = new AddCommand(dc);
        map.put(addCommand.getName(), addCommand);

        RemoveByCharacterCommand removeByCharacter = new RemoveByCharacterCommand(dc);
        map.put(removeByCharacter.getName(), removeByCharacter);

        UpdateIdCommand updateIdCommand = new UpdateIdCommand(dc);
        map.put(updateIdCommand.getName(), updateIdCommand);

        ClearCommand clearCommand = new ClearCommand(dc);
        map.put(clearCommand.getName(), clearCommand);

        HeadCommand headCommand = new HeadCommand(dc);
        map.put(headCommand.getName(), headCommand);

        RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand(dc);
        map.put(removeGreaterCommand.getName(), removeGreaterCommand);

        RemoveByIDCommand removeByIDCommand = new RemoveByIDCommand(dc);
        map.put(removeByIDCommand.getName(), removeByIDCommand);

        GroupCountingByAgeCommand groupCountingByAgeCommand = new GroupCountingByAgeCommand(dc);
        map.put(groupCountingByAgeCommand.getName(), groupCountingByAgeCommand);

        Environment environment = new Environment(map);

        while (in.hasNext()) {
            String line = in.nextLine();
            if (map.keySet().contains(line)) {
                Command command = map.get(line);
                try {
                    command.execute(environment, System.in, System.out);
                }
                catch (CommandException e) {
                    System.err.println(e.getMessage());
                }
            } else {
                System.err.println("Unknown command: " + line + "\n Введите <help> для списка команд");
            }
        }
    }
}