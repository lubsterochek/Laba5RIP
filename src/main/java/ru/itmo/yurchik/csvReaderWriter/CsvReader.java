package ru.itmo.yurchik.csvReaderWriter;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
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
