package ru.kpfu.itis.paramonov.model;

public class Service {
    private int id;

    private int price;

    private String name;

    private int duration;

    public Service(int id, int price, String name, int duration) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
