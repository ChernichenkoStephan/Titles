package com.titles.selectionservice.Model;

import java.io.Serializable;
import java.util.Objects;

public class Preference  implements Serializable {
    protected String name;
    protected Integer value;

    @Override
    public String toString() {
        return "Preference{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    public Preference(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public Preference() { }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Preference that = (Preference) o;
        return name.equals(that.name) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void updateValue(Integer value) {
        this.value += value;
    }
}