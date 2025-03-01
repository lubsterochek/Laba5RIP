package ru.itmo.yurchik.csvReaderWriter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import ru.itmo.yurchik.collection.DragonCollection;
import ru.itmo.yurchik.model.*;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CsvReader {
    private ArrayList<String[]> data;
    private String[] header;
    private InputStreamReader isr;

    public CsvReader(String envName) {
        this.data = new ArrayList<>();
        try {
            String filePath = System.getenv(envName);
            isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
        } catch (FileNotFoundException e) {
            System.out.printf("Задайте в переменной %1$s путь до файла коллекции", envName);
            System.exit(0);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Извините, формат кодировки вашего файла не поддерживается. Попробуйте файл с кодировкой UTF-8.");
            System.exit(0);
        }
    }

    public void read() throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(isr);
        header = reader.readNext();// Считываем заголовок
        String[] nextLine; // Считываем остальные данные
        while ((nextLine = reader.readNext()) != null) {
            data.add(nextLine);
        }
    }

    //Парсим дракона из файла
    private Dragon parseDragon(String line) {
        try {
            String[] values = line.replace("\"", "").split(",");
            String name = values[1];
            float x = Float.parseFloat(values[2]);
            float y = Float.parseFloat(values[3]);
            ZonedDateTime date = ZonedDateTime.parse(values[4], DateTimeFormatter.ISO_ZONED_DATE_TIME);
            Long age = Long.parseLong(values[5]);
            Color color = Color.valueOf(values[6]);
            DragonType type = DragonType.valueOf(values[7]);
            DragonCharacter character = DragonCharacter.valueOf(values[8]);
            float depth = Float.parseFloat(values[9]);
            float numberOfTreasures = Float.parseFloat(values[10]);

            return new Dragon(name, new Coordinates(x, y), date, age, color, type, character, new DragonCave(depth, numberOfTreasures));
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка парсинга строки: " + line);
            return null;
        }
    }

    public void loadDragons(DragonCollection dragonCollection) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(isr);
            String line = reader.readLine();
            while ((line = reader.readLine()) != null) {
                Dragon dragon = parseDragon(line);
                if (dragon != null) {
                    dragonCollection.addDragon(dragon);
                }
            }
        } catch (IOException e){
            System.out.println("Ошибка чтения файл: ");
            System.exit(0);
        }
    }




    public ArrayList<String[]> getData() {
        return data;
    }

    public String[] getHeader() {
        return header;
    }

    public String[] getRow(int index) {
        if (index >= 0 && index < data.size()) {
            return data.get(index);
        } else {
            throw new IllegalArgumentException("Индекс строки вне диапозона");
        }
    }

    public ArrayList<String> getColumn(String columnName) {
        if (header != null) {
            for (int i = 0; i < header.length; i++) {
                if (header[i].equals(columnName)) {
                    ArrayList<String> column = new ArrayList<>();
                    for (String[] row : data) {
                        column.add(row[i]);
                    }
                    return column;
                }
            }
        }
        throw new IllegalArgumentException("Столбец '" + columnName + "' не найден");
    }
}
