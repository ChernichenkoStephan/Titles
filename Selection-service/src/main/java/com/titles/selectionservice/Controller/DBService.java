package com.titles.selectionservice.Controller;

import com.titles.selectionservice.Controller.Interfacies.*;
import com.titles.selectionservice.Model.Paper;
import com.titles.selectionservice.Model.Selection;
import com.titles.selectionservice.Controller.Interfacies.SelectionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBService implements Database {

    public static DBService defaultService =  new DBService();

    private DBService() {
        System.out.println("DBService created");
    }

    public void init(SelectionRepository repository) {
        this.repository = repository;
        System.out.println("DBService initialized");
    }

    private SelectionRepository repository;

    @Override
    public Integer addSelection(Selection selection) {
        try {
            System.out.println("DBService: adding selection...");
            repository.save(selection);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Integer updateSelection(Selection selection) {
        try {
            System.out.println("DBService: updating selection...");
            repository.save(selection);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Integer deleteSelection(Selection selection) {
        try {
            System.out.println("DBService: deleting selection...");
            repository.delete(selection);
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Integer clear() {
        try {
            System.out.println("DBService: deleting all selections...");
            repository.deleteAll();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return 0;
    }

    @Override
    public Optional<Selection> getSelection(Integer accountID) {
        List<Selection> res = new ArrayList<>();
        try {
            System.out.println("DBService: Getting selection from DB");
            List<Selection> resp = repository.getByAccountID(accountID);
            repository.delete(resp.get(0));
            return Optional.ofNullable(resp.get(0));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return Optional.empty();
    }

    @Override
    public List<Selection> getBackup() {
        List<Selection> res = new ArrayList<>();
        try {
            System.out.println("DBService: getting backup...");
            res.addAll(repository.findAll());
            repository.deleteAll();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        return res;
    }

    @Override
    public boolean test() {
        try {
            System.out.println("DBService: trying to fetch something from DB...");
            repository.count();
            return true;
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return false;
        }
    }
}
