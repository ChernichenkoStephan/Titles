package com.titles.profileservice.Model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

public class Bubble {

    @Id
    public String id;

    private final Set<Mem> mems;

    @Override
    public String toString() {
        return "Bubble{" +
                "mems=" + mems +
                '}';
    }

    public Bubble(Set<Mem> mems) {
        this.mems = mems;
    }

    public Set<Mem> getMems() {
        return mems;
    }

    public void addMems(ArrayList<Mem> mems) {
        this.mems.addAll(mems);
    }

    public void addMem(Mem mem) {
        this.mems.add(mem);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bubble other = (Bubble) o;
        int matches = 0;
        for (Mem tm : this.mems) {
            if (other.mems.contains(tm)) matches++;
        }
        return this.mems.size() == matches && this.mems.size() == other.mems.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(mems);
    }
}
