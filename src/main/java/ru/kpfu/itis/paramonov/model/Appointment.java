package ru.kpfu.itis.paramonov.model;

import java.sql.Timestamp;

public class Appointment {
    private int id;

    private int masterId;

    private int serviceId;

    private Timestamp time;

    private String clientNumber;

    public Appointment(int id, int masterId, int serviceId, Timestamp time, String clientNumber) {
        this.id = id;
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.time = time;
        this.clientNumber = clientNumber;
    }

    public Appointment(int masterId, int serviceId, Timestamp time, String clientNumber) {
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.time = time;
        this.clientNumber = clientNumber;
    }

    public Appointment(int masterId, int serviceId, String clientNumber) {
        this.masterId = masterId;
        this.serviceId = serviceId;
        this.clientNumber = clientNumber;
    }

    public int getId() {
        return id;
    }

    public int getMasterId() {
        return masterId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public Timestamp getTime() {
        return time;
    }

    public String getClientNumber() {
        return clientNumber;
    }
}
