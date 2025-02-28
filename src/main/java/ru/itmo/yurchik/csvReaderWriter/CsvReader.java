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

    public CsvReader(String envName){
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
        header = reader.readNext();// Считываем заголовок
        String[] nextLine; // Считываем остальные данные
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
}