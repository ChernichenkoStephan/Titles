package com.titles.profileservice.Model;

import org.springframework.data.annotation.Id;

import java.util.Set;

public class DBProfile extends Profile{

    @Id
    public String id;

    public DBProfile(Integer accountID, Integer lastRequest, Set<Preference> preferences, Set<Bubble> bubbles) {
        super(accountID, lastRequest, preferences, bubbles);
    }

    public void copy(Profile p) {
        this.accountID = p.accountID;
        this.lastRequest = p.lastRequest;
        this.preferences = p.preferences;
        this.bubbles = p.bubbles;
    }

}
