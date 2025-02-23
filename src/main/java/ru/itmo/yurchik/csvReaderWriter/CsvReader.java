package ru.itmo.yurchik.csvReaderWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvReader {
    private FileReader fileReader;
    private ArrayList<String[]> data;
    private String[] header;

    public CsvReader(){
        String envName = "DRAGON_CSV";
        this.data = new ArrayList<>();
        try {
            String filePath = System.getenv(envName);
            fileReader = new FileReader(filePath);
        }
        catch (FileNotFoundException e) {
            System.out.printf("Задайте в переменной %1$s путь до файла коллекции", envName);
            System.exit(0);
        }
    }

    public void read() throws CsvValidationException, IOException {
        CSVReader reader = new CSVReader(fileReader);
        // Считываем заголовок
        header = reader.readNext();
        // Считываем остальные данные
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            data.add(nextLine);
        }
    }

    public String[] getHeader() {
        return header;
    }

    public ArrayList<String[]> getData() {
        return data;
    }

    public String[] getRow(int index) {
        if (index >= 0 && index < data.size()) {
            return data.get(index);
        } else {
            throw new IndexOutOfBoundsException("Индекс строки вне диапазона");
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

    public ArrayList<String[]> filterRows(java.util.function.Predicate<String[]> condition) {
        ArrayList<String[]> filteredData = new ArrayList<>();
        for (String[] row : data) {
            if (condition.test(row)) {
                filteredData.add(row);
            }
        }
        return filteredData;
    }

    /* Пример использования
    public static void main(String[] args) {
        CsvReader csvReader = new CsvReader("data.csv");
        csvReader.read();

        System.out.println("Заголовок:");
        for (String col : csvReader.getHeader()) {
            System.out.print(col + " ");
        }
        System.out.println();

        System.out.println("Данные:");
        for (String[] row : csvReader.getData()) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        System.out.println("Первая строка:");
        String[] firstRow = csvReader.getRow(0);
        for (String cell : firstRow) {
            System.out.print(cell + " ");
        }
        System.out.println();

        System.out.println("Столбец 'Age':");
        ArrayList<String> ageColumn = csvReader.getColumn("Age");
        for (String age : ageColumn) {
            System.out.print(age + " ");
        }
        System.out.println();

        // Фильтрация строк, где возраст больше 25
        ArrayList<String[]> filteredData = csvReader.filterRows(row -> Integer.parseInt(row[1]) > 25);
        System.out.println("Фильтрованные данные (Age > 25):");
        for (String[] row : filteredData) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    } */
}