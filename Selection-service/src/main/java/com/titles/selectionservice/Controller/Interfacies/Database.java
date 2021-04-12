package com.titles.selectionservice.Controller.Interfacies;

import com.titles.selectionservice.Model.Selection;

import java.util.List;
import java.util.Optional;

public interface Database {

    Integer addSelection(Selection selection);
    Integer updateSelection(Selection selection);
    Integer deleteSelection(Selection selection);
    Integer clear();
    Optional<Selection> getSelection(Integer accountID);

    List<Selection> getBackup();

    boolean test();

}
