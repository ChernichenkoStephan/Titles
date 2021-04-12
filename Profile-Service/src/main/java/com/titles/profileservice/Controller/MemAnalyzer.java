package com.titles.profileservice.Controller;

import com.titles.profileservice.Model.Mem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MemAnalyzer implements Analyzer {
    @Override
    public Set<Mem> analyze(List<Mem> mems) {
        return new HashSet<>(mems);
    }
}
