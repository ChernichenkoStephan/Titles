package com.titles.selectionservice.Controller;

import com.titles.selectionservice.Controller.Interfacies.*;
import com.titles.selectionservice.Model.Paper;
import com.titles.selectionservice.Model.Profile;
import com.titles.selectionservice.Model.Selection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class SelectionService {

    private final ProfileFetcher profileFetcher;
    private final PaperFetcher paperFetcher;
    private final Database db;
    private final Selectioner selectioner = Selectioner.defaultService;

    HashMap<Integer, ArrayDeque<Paper>> current = new HashMap<>();

    public static SelectionService defaultService = new SelectionService();

    private SelectionService() {
        this.profileFetcher = ProfileFetcherService.defaultService;
        this.paperFetcher = PaperFetcherService.defaultService;
        this.db = DBService.defaultService;
    }

    public void init(List<Selection> selections) {
        selections.forEach(selection -> current.put(selection.getAccountID(), new ArrayDeque(selection.getPapers())));
        System.out.println("SelectionService initialized");
    }

    public JSONObject update(JSONObject data) {
        System.out.println("Updateing selection on request...");

        // Convert data to papers
        Set<Paper> papers = JSONConverter.toPapers(data);

        // Put them Selection maker
        selectioner.setPapers(papers);

        // Fetch all profiles to update
        Set<Profile> profiles = profileFetcher.fetch();

        profiles.forEach(profile -> {

            // Making new selection
            Selection newSelection = selectioner.make(profile.getAccountID());

            // Saving to current stack
            updateStack(newSelection);

            // Saving to db for backup
            db.addSelection(newSelection);

        });

        System.out.println("Current size updated: " + current.size());

        JSONObject resp = new JSONObject();
        resp.put("code", 1);
        resp.put("massage", "Success");

        return resp;
    }

    // All current selections
    public JSONObject getSelections(Integer key) {
        HashSet<Paper> papers = new HashSet<>();
        current.forEach((id, sel) -> papers.addAll(sel));
        return JSONConverter.toJSON(papers, Paper.class);
    }

    // Get selection for user
    public JSONObject getSelection(Integer userId) {

        JSONObject resp = new JSONObject();
        Optional<Set<Paper>> papers;
        Integer code = 0;
        String massage = "You have read everything (:";

        // Put empty array for default respond
        resp.put("papers", new JSONArray());

        // Getting current papers
        papers = getCurrent(userId);

        // If there is no papers - update selection
        if (papers.isPresent()) {
            // Getting new respond with papers
            resp = getJSONPapers(papers.get());
            code = 1;
            massage = "Success";
            return resp;
        }

        // If nothing to send, send appropriate respond
        resp.put("code", code);
        resp.put("massage", massage);
        return resp;
    }

    private Optional<Set<Paper>> getCurrent(Integer userId) {

        System.out.println("Getting current papers...");

        Set<Paper> res = new HashSet<>();
        Optional<Deque<Paper>> papers = Optional.empty();

        // check in current
        if (current.containsKey(userId)) papers = Optional.ofNullable(current.get(userId));

        // if there is selection in current
        papers.ifPresent( p -> {
            System.out.println("Found papers in current.");
            int counter = 0;

            // get first 30
            while (!p.isEmpty() && counter != 30) {
                res.add(p.pop());
                counter++;
            }

            System.out.println(counter + "'s Papers loaded from current.");
        });

        // Updating in case selection was not found or empty
        if (res.isEmpty()) {
            // update current & check again
            System.out.println("Trying to update...");

            if (updateCurrent(userId))
                return getCurrent(userId);
        }

        // Send empty if there is no papers
        if (res.isEmpty()) { return Optional.empty(); }

        return Optional.of(res);
    }

    private Boolean updateCurrent(Integer userId) {
        System.out.println("Updating current...");

        // Fetch new papers to update
        Set<Paper> papers = paperFetcher.fetch();
        selectioner.setPapers(papers);

        // Fetch new profile to update
        Optional<Profile> profile = profileFetcher.fetch(userId);
        if(profile.isEmpty()) return false;

        selectioner.setProfile(profile.get());

        // Make new selection if is not
        Selection selection = selectioner.make(userId);

        // Save to db for backup
        System.out.println("Saving to DB");
        db.addSelection(selection);

        // Save to current
        System.out.println("Saving to current");
        updateStack(selection);

        System.out.println("Current size updated: " + current.size());

        if (selection.getPapers().isEmpty()) return false;

        return true;
    }

    private void updateStack(Selection selection) {
        int id = selection.getAccountID();

        // if there is profile in current update, else make new
        if (current.containsKey(id)) {
            System.out.println("Profile in current found.");

            ArrayDeque<Paper> papers = current.get(id);

            selection.getPapers().forEach( paper -> {
                // If this is new paper add it
                if(!papers.contains(paper)) {
                    papers.add(paper);
                }
            });

        } else {
            System.out.println("Profile in current not found making new...");

            ArrayDeque<Paper> papers = new ArrayDeque<>(selection.getPapers());
            current.put(id, papers);
        }



    }

    private JSONObject getJSONPapers(Set<Paper> papers) {
        return JSONConverter.toJSON(papers, Paper.class);
    }

    public JSONObject test() {
        JSONObject resp = new JSONObject();
        int code = 4;
        String massage = "Fault, db and converter doesn't work";
        if (db.test()) {
            code = 2;
            massage = "Fault, converter doesn't work";
            if (JSONConverter.test()) {
                code = 1;
                massage = "Everything is ok, db and converter works fine";
            }
        } else {
            code = 3;
            massage = "Fault, db doesn't work";
        }

        resp.put("code", code);
        resp.put("message", massage);
        return resp;
    }

}
