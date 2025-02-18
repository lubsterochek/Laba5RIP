package ru.itmo.yurchik.model;

import java.time.ZonedDateTime;

public class Dragon {
    private final int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long age; //Значение поля должно быть больше 0, Поле может быть null
    private Color color; //Поле может быть null
    private DragonType type; //Поле не может быть null
    private DragonCharacter character; //Поле может быть null
    private DragonCave cave; //Поле не может быть null

    public Dragon(int id, String name, Coordinates coordinates, ZonedDateTime creationDate, Long age, Color color, DragonType type, DragonCharacter character, DragonCave cave) {
        if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("id не может быть null и должен быть больше 0");
        }

        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate(creationDate);
        this.setAge(age);
        this.setColor(color);
        this.setType(type);
        this.setCharacter(character);
        this.setCave(cave);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("name не может быть 0 и не может быть пустым");
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        if (coordinates != null) {
            this.coordinates = coordinates;
        } else {
            throw new IllegalArgumentException("координаты не могут быть null");
        }
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        if (creationDate != null) {
            this.creationDate = creationDate;
        } else {
            throw new IllegalArgumentException("ZoneDateTime не может быть пустым или отрицательным");
        }
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        if (age != null && age > 0) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age не может быть пустым или отрицательным");
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        if (color != null) {
            this.color = color;
        } else {throw new IllegalArgumentException("Color не может быть пустым");}
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        if (type != null) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Type не может быть пустым");
        }
    }

    public DragonCharacter getCharacter() {
        return character;
    }

    public void setCharacter(DragonCharacter character) {
        if (character != null) {
            this.character = character;
        } else {throw new IllegalArgumentException("Character не может быть пустым");}
    }

    public DragonCave getCave() {
        return cave;
    }

    public void setCave(DragonCave cave) {
        if (cave != null) {
            this.cave = cave;
        } else {
            throw new IllegalArgumentException("Cave не может быть пустым");
        }
    }
}
