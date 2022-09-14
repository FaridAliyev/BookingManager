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

    public String getSurname() {
        return surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passenger)) return false;
        Passenger passenger = (Passenger) o;
        return getId().equals(passenger.getId()) && getName().equals(passenger.getName()) && getSurname().equals(passenger.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname());
    }
}
