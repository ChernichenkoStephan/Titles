package com.titles.profileservice.Controller;

import com.titles.profileservice.Model.Mem;

import java.util.List;
import java.util.Set;

public interface Analyzer {
    Set<Mem> analyze(List<Mem> mems);
}
