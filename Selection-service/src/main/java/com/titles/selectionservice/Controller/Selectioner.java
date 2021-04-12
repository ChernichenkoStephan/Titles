package com.titles.selectionservice.Controller;

import com.titles.selectionservice.Controller.Interfacies.SelectionMaker;
import com.titles.selectionservice.Model.*;

import java.util.*;

public class Selectioner implements SelectionMaker {

    public static Selectioner defaultService = new Selectioner();
    private Selectioner() { }

    private Profile profile;
    private Set<Paper> papers;

    public Selectioner(Profile profile, Set<Paper> papers) {
        this.profile = profile;
        this.papers = papers;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setPapers(Set<Paper> papers) {
        this.papers = papers;
    }

    @Override
    public Selection make(Integer userId) {
        System.out.println("Making new selection");

        for(Paper paper : papers){
            Integer coeff = makeCoefficient(paper);
            paper.setScore(coeff);
        }

        Comparator<Paper> comparator = new Comparator<Paper>() {
            @Override
            public int compare(Paper o1, Paper o2) {
                if(o1.getScore()> o2.getScore()) return -1;
                else if(o1.getScore() < o2.getScore()) return 1;
                else return 0;
            }
        };

        papers.stream().sorted(comparator);

        return new Selection(userId, papers);
    }


    private Integer makeCoefficient(Paper paper){

        Integer coefficient=0;

        for(Mem mem : paper.getMems()){

            for(Preference preference : profile.getPreferences()){

                if(preference.getName().equals(mem.getName())){

                    coefficient+=preference.getValue();

                    break;
                }
            }
        }

        return coefficient;
    }

}
