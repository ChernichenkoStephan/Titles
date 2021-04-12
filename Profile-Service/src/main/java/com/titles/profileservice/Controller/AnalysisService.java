package com.titles.profileservice.Controller;

import com.titles.profileservice.Controller.Interfaces.Analyser;
import com.titles.profileservice.Model.Bubble;
import com.titles.profileservice.Model.Mem;
import com.titles.profileservice.Model.Preference;
import com.titles.profileservice.Model.Profile;

import java.util.HashSet;
import java.util.Set;

public class AnalysisService implements Analyser {

    private final Profile profile;
    private final Set<Preference> preferences;
    private final Set<Bubble> bubbles;
    private Set<Mem> mems;

    public AnalysisService(Profile profile, Set<Preference> preferences, Set<Bubble> bubbles, Set<Mem> mems) {
        this.profile = profile;
        this.preferences = preferences;
        this.bubbles = bubbles;
        this.mems = mems;
    }

    @Override
    public Profile analyze() {

        // Performing difference
        analyzeDelta();

        // Analyzing bubbles in correlation with preferences
        analyzeBubbles();

        // Adding finish adjustments with mems popularity
        analyzeMems();

        return profile;
    }

    private void analyzeDelta() {
        Set<Preference> prew = profile.getPreferences();

        HashSet<Preference> after = new HashSet<>(prew);

        for (Preference np: preferences) {
            boolean isExist = false;
            for (Preference pp : prew) {
                if (pp.getName().equals(np.getName())) {
                    pp.updateValue(np.getValue());
                    isExist = true;
                    break;
                }
            }
            if (!isExist) after.add(np);
        }

        profile.setPreferences(after);
    }

    private void analyzeBubbles() {
        profile.setBubbles(bubbles);
    }

    private void analyzeMems() {
        return;
    }
}
