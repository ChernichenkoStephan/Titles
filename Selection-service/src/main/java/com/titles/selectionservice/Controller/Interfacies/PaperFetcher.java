package com.titles.selectionservice.Controller.Interfacies;

import com.titles.selectionservice.Model.Paper;

import java.util.Set;

public interface PaperFetcher {
    Set<Paper> fetch();
}
