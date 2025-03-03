package ru.itmo.yurchik.csvReaderWriter;

import com.opencsv.CSVWriter;
import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.command.exception.CommandException;
import ru.itmo.yurchik.model.Dragon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.CoderMalfunctionError;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;

/**
 * Класс для записи в CSV
 */
public class CsvWriter {
    /**
     * Проверка переменной окружения + запись полей в файл
     * @param collection
     * @throws IOException
     */
    public static void saveToCSV(DragonCollection collection) throws IOException {
        String filePath = System.getenv("DRAGON_CSV");
        if (filePath == null) {
            throw new IOException("Ошибка: Переменная окружения DRAGON_CSV не установлена!");
        }

        try (FileOutputStream fos = new FileOutputStream(filePath);
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8))) {

            writer.writeNext(new String[]{"ID", "Name", "Coordinates X", "Y", "ZoneDate", "Age", "Color", "Type", "Character", "Depth", "NumberOfTreasures"});

            for (Dragon dragon : collection.getDragons()) {
                writer.writeNext(new String[]{
                        String.valueOf(dragon.getId()),
                        dragon.getName(),
                        String.valueOf(dragon.getCoordinates().getX()),
                        String.valueOf(dragon.getCoordinates().getY()),
                        dragon.getCreationDate().format(DateTimeFormatter.ISO_ZONED_DATE_TIME),
                        String.valueOf(dragon.getAge()),
                        dragon.getColor().name(),
                        dragon.getType().name(),
                        dragon.getCharacter().name(),
                        String.valueOf(dragon.getCave().getDepth()),
                        dragon.getCave().getNumberOfTreasures() != null ? String.valueOf(dragon.getCave().getNumberOfTreasures()) : "null"
                });
            }

            System.out.println("Файл успешно сохранён: " + filePath);
        }
    }
}
