package com.titles.profileservice.Model;

import java.util.Objects;
import java.util.Set;

public class Profile {

    protected Integer accountID;
    protected Integer lastRequest;
    protected Set<Preference> preferences;
    protected Set<Bubble> bubbles;


    public Profile(Integer accountID, Integer lastRequest, Set<Preference> preferences, Set<Bubble> bubbles) {
        this.accountID = accountID;
        this.lastRequest = lastRequest;
        this.preferences = preferences;
        this.bubbles = bubbles;
    }

    public DBProfile toDBP () {
        return new DBProfile(this.accountID, this.lastRequest, this.preferences, this.bubbles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return accountID.equals(profile.accountID) &&
                lastRequest.equals(profile.lastRequest) &&
                same(this.preferences, profile.preferences);
    }

    private static boolean same(Set<Preference> l, Set<Preference> r) {
        int matches = 0;
        for (Preference lp : l) {
            if (r.contains(lp)) matches++;
        }
        return l.size() == matches && l.size() == r.size();
    }

    @Override
    public String toString() {
        return "Profile{ accountID=" + accountID +
                ", lastRequest=" + lastRequest +
                ", preferences=" + preferences +
                ", bubbles=" + bubbles +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, lastRequest, preferences);
    }

    public Integer getAccountID() {
        return accountID;
    }

    public Integer getLastRequest() {
        return lastRequest;
    }

    public void setLastRequest(Integer lastRequest) {
        this.lastRequest = lastRequest;
    }

    public Set<Preference> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Preference> preferences) {
        this.preferences = preferences;
    }

    public Set<Bubble> getBubbles() { return bubbles; }

    public void setBubbles(Set<Bubble> bubbles) {
        this.bubbles = bubbles;
    }
}
