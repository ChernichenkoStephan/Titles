package com.titles.databaseservice.Model;

import java.util.Objects;

public class Mem {
    protected String name;
    protected Integer popularityCoefficient;

    public Mem()
    {
        this.name = "name";
        this.popularityCoefficient = 0;
    }

    public Mem(String name, Integer popularityCoefficient) {
        this.name = name;
        this.popularityCoefficient = popularityCoefficient;
    }

    public String getName() {
        return name;
    }

    public Integer getPopularityCoefficient() {
        return popularityCoefficient;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopularityCoefficient(Integer popularityCoefficient) {
        this.popularityCoefficient = popularityCoefficient;
    }

    @Override
    public String toString() {
        return "Mem{" +
                "name='" + name + '\'' +
                ", popularityCoefficient=" + popularityCoefficient +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mem mem = (Mem) o;
        return name.equals(mem.name) && popularityCoefficient.equals(mem.popularityCoefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, popularityCoefficient);
    }
}
