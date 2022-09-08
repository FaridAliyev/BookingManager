package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Passenger implements Serializable {
    private final UUID id;
    private String name;
    private String surname;

    private Passenger() {
        this.id = UUID.randomUUID();
    }

    public Passenger(String name, String surname) {
        this();
        this.name = name;
        this.surname = surname;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return id.equals(passenger.id) && getName().equals(passenger.getName()) && getSurname().equals(passenger.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getName(), getSurname());
    }
}
