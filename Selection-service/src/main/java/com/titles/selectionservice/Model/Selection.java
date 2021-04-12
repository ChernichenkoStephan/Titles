package com.titles.selectionservice.Model;

import org.springframework.data.annotation.Id;

import java.util.*;

public class Selection {

    @Id
    public String mID;

    private final Integer accountID;
    private final Set<Paper> papers;

    public Selection(Integer accountID, Set<Paper> papers) {
        this.accountID = accountID;
        this.papers = papers;
    }

    public void addPaper(Paper paper) {
        papers.add(paper);
    }

    public void addPapers(Set<Paper> paper) {
        papers.addAll(paper);
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Set<Paper> getPapers() {
        return papers;
    }

    @Override
    public String toString() {
        return "Selection{" +
                ", accountID=" + accountID +
                ", papers=" + papers +
                '}';
    }
}
