package Bank.Client;

import java.util.*;

public class Client {

    private final UUID id;
    private final String name;
    private final String surname;
    private String address;
    private Integer passport;
    private boolean sus;

    public Client(String name, String surname) {
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        address = null;
        passport = null;
        sus = true;
    }

    public Client(String name, String surname, String address) {
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.address = address;
        passport = null;
        sus = true;
    }

    public Client(String name, String surname, Integer passport) {
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        address = null;
        this.passport = passport;
        sus = true;
    }

    public Client(String name, String surname, String address, Integer passport) {
        id = UUID.randomUUID();
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.passport = passport;
        sus = false;
    }

    public void updateInfo(String address, Integer passport) {
        this.address = address;
        this.passport = passport;
        sus = false;
    }

    public void updateInfo(String address) {
        this.address = address;
        if (passport != null)
            sus = false;
    }

    public void updateInfo(Integer passport) {
        this.passport = passport;
        if (address != null)
            sus = false;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPassport() {
        return passport;
    }

    public boolean isSus() {
        return sus;
    }
}
