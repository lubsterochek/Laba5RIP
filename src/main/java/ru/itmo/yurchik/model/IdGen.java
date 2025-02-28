package ru.itmo.yurchik.model;
import java.util.HashSet;

public class IdGen {
    private static int idCounter = 1;
    private static HashSet<Integer> usedIds = new HashSet<>();

    // 🔹 Генерирует уникальный ID
    public static int generateId() {
        while (usedIds.contains(idCounter)) {
            idCounter++; // Ищем первый свободный ID
        }
        usedIds.add(idCounter);
        return idCounter;
    }

    // 🔹 Регистрирует ID (если уже существует — выбрасывает ошибку!)
    public static void registerId(int id) {
        if (usedIds.contains(id)) {
            throw new IllegalArgumentException("Ошибка: ID " + id + " уже используется!");
        }
        usedIds.add(id);
        idCounter = Math.max(idCounter, id + 1);
    }

    // 🔹 Освобождает ID (удаление дракона)
    public static void releaseId(int id) {
        usedIds.remove(id);
    }
}
