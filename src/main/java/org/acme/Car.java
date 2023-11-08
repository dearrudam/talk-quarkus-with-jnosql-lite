package org.acme;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

import java.util.Objects;
import java.util.UUID;

@Entity
public class Car {

    static Car create(String owner) {
        return new Car(UUID.randomUUID().toString(), owner);
    }

    @Id
    private String id;
    @Column
    private String owner;

    /**
     * do not use it
     */
    @Deprecated
    public Car() {
    }

    public Car(String id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
