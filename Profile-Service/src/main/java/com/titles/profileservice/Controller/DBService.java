package com.titles.profileservice.Controller;


import com.titles.profileservice.Controller.Interfaces.Mongo.BubbleRepository;
import com.titles.profileservice.Controller.Interfaces.Database;
import com.titles.profileservice.Controller.Interfaces.Mongo.MemRepository;
import com.titles.profileservice.Controller.Interfaces.Mongo.ProfileRepository;
import com.titles.profileservice.Model.*;

import java.util.*;

public class DBService implements Database {

    public static DBService defaultService = new DBService();

    private MemRepository memRepository;

    private ProfileRepository profileRepository;

    private BubbleRepository bubbleRepository;

    private DBService() { }

    public void init(MemRepository memRepository, ProfileRepository profileRepository, BubbleRepository bubbleRepository) {
        this.memRepository = memRepository;
        this.profileRepository = profileRepository;
        this.bubbleRepository = bubbleRepository;
    }

    @Override
    public void addProfile(Profile profile) {
        DBProfile dbp = profile.toDBP();
        profileRepository.save(dbp);
    }

    @Override
    public void updateProfile(Profile profile) {
        List<DBProfile> response = profileRepository.getByAccountID(profile.getAccountID());
        if (response.isEmpty()) {return;}
        DBProfile profileToUpdate = response.get(0);
        profileToUpdate.copy(profile);
        profileRepository.save(profileToUpdate);
    }

    @Override
    public void deleteProfileByID(Integer userID) {
        List<DBProfile> response = profileRepository.getByAccountID(userID);
        if (response.isEmpty()) {return;}
        DBProfile profileToDelete = response.get(0);
        profileRepository.delete(profileToDelete);
    }

    @Override
    public Optional<Profile> getProfileByID(Integer userID) {
        List<DBProfile> response = profileRepository.getByAccountID(userID);
        if (response.isEmpty()) {return Optional.empty();}
        return Optional.of((Profile) response.get(0));
    }

    @Override
    public Optional<List<Profile>> getProfiles() {
        List<DBProfile> response = profileRepository.findAll();
        return Optional.of(new ArrayList<Profile> (response));
    }

    @Override
    public Optional<Set<Mem>> getMems() {
        List<MemsPack> response = memRepository.findAll();
        if (response.isEmpty()) {return Optional.empty();}
        return Optional.of(response.get(0).getMems());
    }

    @Override
    public void setMems(Set<Mem> mems) {
        List<MemsPack> response = memRepository.findAll();
        if (response.isEmpty()) {
            MemsPack pack = new MemsPack(mems);
            memRepository.save(pack);
        } else {
            MemsPack pack = response.get(0);
            HashSet<Mem> baseMems = new HashSet<>(pack.getMems());
            baseMems.addAll(mems);
            pack.setMems(baseMems);
            memRepository.save(pack);
        }
    }

    @Override
    public void addMem(String name, Integer coefficent) {
        List<MemsPack> response = memRepository.findAll();
        if (response.isEmpty()) {
            MemsPack pack = new MemsPack(Set.of(new Mem(name, coefficent)));
            memRepository.save(pack);
        } else {
            MemsPack pack = response.get(0);
            HashSet<Mem> baseMems = new HashSet<>(pack.getMems());
            baseMems.add(new Mem(name, coefficent));
            pack.setMems(baseMems);
            memRepository.save(pack);
        }
    }

    @Override
    public Optional<Set<Bubble>> getBubbles() {
        List<Bubble> response = bubbleRepository.findAll();
        HashSet<Bubble> res = new HashSet<>(response);
        if (response.isEmpty()) {return Optional.empty();}
        return Optional.of(res);
    }

    @Override
    public void setBubbles(Set<Bubble> bubbles) {
        for (Bubble bubble: bubbles) bubbleRepository.save(bubble);
    }

    @Override
    public void addBubble(Bubble bubble) {
        bubbleRepository.save(bubble);
    }

    @Override
    public void clear() {
        bubbleRepository.deleteAll();
        memRepository.deleteAll();
        profileRepository.deleteAll();
    }

    @Override
    public void clearMems() {
        memRepository.deleteAll();

    }

    @Override
    public void clearBubbles() {
        bubbleRepository.deleteAll();
    }

    @Override
    public void clearProfiles() {
        profileRepository.deleteAll();
    }

    @Override
    public boolean test() {

        clear();

        Preference p1 = new Preference("mem1", 10);
        Preference p2 = new Preference("mem2", 20);
        Preference p3 = new Preference("mem3", 30);

        Mem m1 = new Mem("mem1", 1);
        Mem m2 = new Mem("mem2", 2);
        Mem m3 = new Mem("mem3", 3);
        Mem m4 = new Mem("mem4", 4);

        HashSet<Mem> mems1  = new HashSet<>(List.of(m1, m2));
        HashSet<Mem> mems2  = new HashSet<>(List.of(m2, m3));

        Bubble bubble1 = new Bubble(mems1);
        Bubble bubble2 = new Bubble(mems2);

        Profile profile = new Profile(1,
                1111,
                Set.of(p1, p2, p3),
                Set.of(bubble1));

        addProfile(profile);
        setBubbles(Set.of(bubble1, bubble2));
        setMems(Set.of(m1, m2, m3, m4));

        Optional<Profile> profResp = getProfileByID(1);
        Optional<List<Profile>> profsResp = getProfiles();
        Optional<Set<Mem>> memResp = getMems();
        Optional<Set<Bubble>> bubbResp = getBubbles();

        clear();

        return profResp.isPresent() && profsResp.isPresent() && memResp.isPresent() && bubbResp.isPresent();
    }

}
