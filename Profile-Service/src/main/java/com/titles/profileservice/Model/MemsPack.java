package com.titles.profileservice.Model;

import org.springframework.data.annotation.Id;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MemsPack {
    @Id
    public String id;

    @Override
    public String toString() {
        return "MemsPack{" +
                "id='" + id + '\'' +
                ", mems=" + mems +
                '}';
    }

    private Set<Mem> mems;

    public MemsPack(Set<Mem> mems) {
        this.mems = mems;
    }

    public MemsPack() { }

    public Set<Mem> getMems() {
        return mems;
    }

    public void setMems(Set<Mem> mems) {
        this.mems = mems;
    }
}
