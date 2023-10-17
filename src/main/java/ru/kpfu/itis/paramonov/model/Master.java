package ru.kpfu.itis.paramonov.model;

public class Master {
    private int id;
    private String name;
    private String lastname;

    private String beginWorkTime;

    private String finishWorkTime;

    public Master(int id, String name, String lastname, String beginWorkTime, String finishWorkTime) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.beginWorkTime = beginWorkTime;
        this.finishWorkTime = finishWorkTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBeginWorkTime() {
        return beginWorkTime;
    }

    public String getFinishWorkTime() {
        return finishWorkTime;
    }
}
