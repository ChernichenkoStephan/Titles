package com.titles.profileservice.Controller;

import com.titles.profileservice.Controller.Interfaces.Analyser;
import com.titles.profileservice.Controller.Interfaces.Database;
import com.titles.profileservice.Model.*;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class ProfileService {

    public static ProfileService defaultService = new ProfileService();

    private Database db;

    public void init(Database db) {
        this.db = db;
    }

    private ProfileService() { }

    /**
     * Method for api
     * @return all profiles from db
     */
    public JSONObject getProfiles()  {
        Optional<List<Profile>> profiles = db.getProfiles();
        if (profiles.isPresent()) {
            List<Profile> pfdb = profiles.get();
            Set<Profile> ps = new HashSet<>(pfdb);
            JSONObject jp = JSONConverter.toJSON(ps, Profile.class);
            return jp;
        }
        return null;
    }

    /**
     * Method for api
     * @param userID internal id
     * @return profile from db
     */
    public JSONObject getProfile(Integer userID) {
        Optional<Profile> profile = db.getProfileByID(userID);
        return profile.map(JSONConverter::toJSON).orElse(null);
    }

    /**
     * Method for api
     * @param userID internal id
     * @param preferences new preferences
     * @return proper response with massage and code
     */
    public JSONObject setPreferences(Integer userID, JSONObject preferences) {
        Optional<Profile> resp = db.getProfileByID(userID);

        if (resp.isPresent()) {
            Profile profile = resp.get();
            Set<Preference> newp = JSONConverter.toPreferences(preferences);

            Analyser analyser = new AnalysisService(profile,
                    newp,
                    db.getBubbles().orElse(Set.of()),
                    db.getMems().orElse(Set.of())
            );

            Profile updated = analyser.analyze();
            db.updateProfile(profile);
            return makeResponce(1, "Success");
        }

        return makeResponce(0, "Failure");
    }

    /**
     * Method for api
     * @param userID internal id
     * @return response with init memes
     */
    public JSONObject newProfile(Integer userID) {
        JSONObject resp;
        HashSet<String> mems = new HashSet();

        // Protect from duplicates
        if (db.getProfileByID(userID).isPresent()) {
            resp = makeResponce(0, "Failure, user already exist");
            resp.put("mems", mems);
            return resp;
        }

        // Making new profile
        Profile profile = new Profile(userID, 1111, Set.of(), Set.of());

        // Saving new profile to db
        db.addProfile(profile);

        // Putting first memes
        resp = makeResponce(1, "Success");
        mems.addAll(Set.of("HackerNews", "NYTimes", "NewsAPI"));
        resp.put("mems", mems);

        return resp;
    }

    /**
     * Method for api
     * @return set af all current memes
     */
    public JSONObject getMems()  {
        Optional<Set<Mem>> mems = db.getMems();
        return mems.map(memSet -> JSONConverter.toJSON(memSet, Mem.class)).orElse(null);
    }

    /**
     * Method for api
     * @param mems input memes for db
     * @return proper response with massage and code
     */
    public JSONObject setMems(JSONObject mems)  {
        List<Mem> newMems = JSONConverter.toMemes(mems);
        Analyzer analyzer = new MemAnalyzer();
        db.setMems(analyzer.analyze(newMems));
        return makeResponce(1, "Success");
    }

    public JSONObject getBubbles()  {
        Optional<Set<Bubble>> bubbles = db.getBubbles();
        return bubbles.map(bubbleSet -> JSONConverter.toJSON(bubbleSet, Bubble.class)).orElse(null);
    }

    /**
     * Method for api
     * @param bubbles input bubbles for db
     * @return proper response with massage and code
     */
    public JSONObject setBubbles(JSONObject bubbles)  {
        Set<Bubble> res = JSONConverter.toBubbles(bubbles);
        for (Bubble b : res)
            db.addBubble(b);
        return makeResponce(1, "Success");
    }

    /**
     * Internal infrastructure method
     * @return proper response with massage and code
     */
    public JSONObject clear() {
        db.clear();
        return makeResponce(1, "Success");
    }

    /**
     * Method for making custom json response
     * @param code response code
     * @param massage response massage
     * @return custom json response
     */
    private JSONObject makeResponce(Integer code, String massage) {
        JSONObject resp = new JSONObject();
        resp.put("code", code);
        resp.put("message", massage);
        return resp;
    }

    /**
     * Internal infrastructure method, for testing current status
     * @return proper response with massage and code
     */
    public JSONObject test()  {
        if (db.test()) {
            if (JSONConverter.converterTest()) {
                return makeResponce(1, "Working right, db fine, converts fine");
            }
            else {
                return makeResponce(2, "Working bad, converts broke");

            }
        } else if (JSONConverter.converterTest()) {
            return makeResponce(3, "Working bad, db bad, converts fine");
        }
        return makeResponce(4, "Working bad, db bad, converts bad");

    }




}
