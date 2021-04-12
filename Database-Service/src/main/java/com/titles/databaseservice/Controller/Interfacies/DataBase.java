package com.titles.databaseservice.Controller.Interfacies;

import com.titles.databaseservice.Model.HotPaper;
import com.titles.databaseservice.Model.Paper;

import java.util.List;
import java.util.Set;

public interface DataBase {
    public Set<Paper> findAll();
    public List<HotPaper> findAllForTime();
    public void savePaper(Set<Paper> paper);
    public Paper findByID(Integer id);
    public void saveHpPaper(HotPaper paper, Integer paperID);
    public void saveOnePaper(Paper paper);
    public void deleteByID(Integer paperID);
    public void deleteAll(Integer key);
    public List<String> findMem();
}
