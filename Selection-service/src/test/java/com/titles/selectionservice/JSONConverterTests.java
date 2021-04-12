package com.titles.selectionservice;

import com.titles.selectionservice.Controller.JSONConverter;
import com.titles.selectionservice.Model.*;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class JSONConverterTests {

    // Asset

    Preference p1 = new Preference("mem1", 10);
    Preference p2 = new Preference("mem2", 20);
    Preference p3 = new Preference("mem3", 30);

    Mem m1 = new Mem("mem1", 1);
    Mem m2 = new Mem("mem2", 2);

    HashSet<Mem> mems1  = new HashSet<>(List.of(m1, m2));

    Bubble bubble = new Bubble(mems1);

    Content content = new Content(1, "url");

    Profile profile = new Profile(1,
            1111,
            Set.of(p1, p2, p3),
            Set.of(bubble));

    Set<Preference> prefs = Set.of(p1);
    Set<Bubble>     bubbs = Set.of(bubble);
    Set<Profile>    profs = Set.of(profile);

    JSONObject jmems = JSONConverter.toJSON(mems1, Mem.class);
    JSONObject jpref = JSONConverter.toJSON(prefs, Preference.class);
    JSONObject jbubb = JSONConverter.toJSON(bubbs, Bubble.class);
    JSONObject jprof = JSONConverter.toJSON(profs, Profile.class);


    @Test
    void memConvertationTest() {

        Mem m1 = new Mem("mem1", 1);

        JSONObject jmem  = JSONConverter.toJSON(m1);
        Mem cmem         = JSONConverter.toMem(jmem).get();

        assertThat(m1).isEqualTo(cmem);

    }

    @Test
    void preferenceConvertationTest() {

        Preference p1 = new Preference("mem1", 10);
        JSONObject jpref = JSONConverter.toJSON(Set.of(p1), Preference.class);
        Preference cpref = (new ArrayList<>(JSONConverter.toPreferences(jpref))).get(0);

        assertThat(p1).isEqualTo(cpref);

    }

    @Test
    void bubbleConvertationTest() {


        Mem m1 = new Mem("mem1", 1);
        Mem m2 = new Mem("mem2", 2);
        HashSet<Mem> mems1  = new HashSet<>(List.of(m1, m2));
        Bubble bubble = new Bubble(mems1);
        JSONObject jbubb = JSONConverter.toJSON(bubble);
        Bubble cbubb     = JSONConverter.toBubble(jbubb).get();

        assertThat(bubble).isEqualTo(cbubb);

    }

    @Test
    void profileConvertationTest() {

        Preference p1 = new Preference("mem1", 10);
        Preference p2 = new Preference("mem2", 20);
        Preference p3 = new Preference("mem3", 30);

        Mem m1 = new Mem("mem1", 1);
        Mem m2 = new Mem("mem2", 2);

        HashSet<Mem> mems1  = new HashSet<>(List.of(m1, m2));

        Bubble bubble = new Bubble(mems1);


        Profile profile = new Profile(1,
                1111,
                Set.of(p1, p2, p3),
                Set.of(bubble));

        JSONObject jprof = JSONConverter.toJSON(profile);
        Profile cprof    = JSONConverter.toProfile(jprof).get();

        assertThat(profile).isEqualTo(profile);

    }

    @Test
    void praperConvertationTest() {

        Mem m1 = new Mem("mem1", 1);
        Mem m2 = new Mem("mem2", 2);

        HashSet<Mem> mems1  = new HashSet<>(List.of(m1, m2));

        Bubble bubble = new Bubble(mems1);

        Content content = new Content(1, "url");
        Integer score = 1;
        Long time = 1L;
        Paper paper = new Paper("source",
                score,
                mems1,
                time,
                "url",
                "author",
                "title",
                "description",
                "body",
                Set.of(content)
        );

        JSONObject jpapr = JSONConverter.paperToJSON(paper);
        Paper cpaper     = JSONConverter.toPaper(jpapr).get();

        assertThat(paper).isEqualTo(cpaper);

    }

    @Test
    void setConvertationTest() {

         // Act

        Set<Mem>        cmems = JSONConverter.toMemes(jmems);
        Set<Preference> cpref = JSONConverter.toPreferences(jpref);
        Set<Bubble>     cbubb = JSONConverter.toBubbles(jbubb);
        Set<Profile>    cprof = JSONConverter.toProfiles(jprof);

        // Assert

        assertThat(mems1).isEqualTo(cmems);
        assertThat(prefs).isEqualTo(cpref);
        assertThat(bubbs).isEqualTo(cbubb);
        assertThat(profs).isEqualTo(cprof);

        try {

            JSONObject jmemse = JSONConverter.toJSON(mems1, Preference.class);
            JSONObject jprefe = JSONConverter.toJSON(prefs, Mem.class);
            JSONObject jbubbe = JSONConverter.toJSON(bubbs, Preference.class);
            JSONObject jprofe = JSONConverter.toJSON(profs, Preference.class);

        } catch (Exception e) {
            assertThat(e.getClass()).isEqualTo(ClassCastException.class);
        }

    }
}
