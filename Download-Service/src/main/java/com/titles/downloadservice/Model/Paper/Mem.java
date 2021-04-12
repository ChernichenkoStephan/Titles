package com.titles.downloadservice.Model.Paper;

import java.io.Serializable;
import java.util.Objects;

public class Mem {

    private final String name;
    private final Integer popularityCoefficient;

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
