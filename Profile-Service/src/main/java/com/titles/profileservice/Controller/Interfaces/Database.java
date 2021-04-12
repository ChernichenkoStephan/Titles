package com.titles.profileservice.Controller.Interfaces;

import com.titles.profileservice.Model.Bubble;
import com.titles.profileservice.Model.Mem;
import com.titles.profileservice.Model.Profile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface Database {

    void addProfile(Profile profile);
    void updateProfile(Profile profile);
    void deleteProfileByID(Integer userID);
    Optional<Profile> getProfileByID(Integer userID);
    Optional<List<Profile>> getProfiles();

    Optional<Set<Mem>> getMems();
    void setMems(Set<Mem> mems);
    void addMem(String name, Integer coefficent);

    Optional<Set<Bubble>> getBubbles();
    void setBubbles(Set<Bubble> bubbles);
    void addBubble(Bubble bubble);

    void clear();
    void clearMems();
    void clearBubbles();
    void clearProfiles();

    public boolean test();
}
