package com.titles.selectionservice.Controller;

import com.titles.selectionservice.Model.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class JSONConverter {

    /* -------------------------- From JSON to POJO -------------------------- */

    public static Optional<Paper> toPaper(JSONObject object) {

        Optional<Paper> res = Optional.empty();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jp = (JSONObject) obj;

            String source = (String) jp.get("source");
            Long score = (Long) jp.get("score");
            Long time = (Long) jp.get("time");
            String sourceUrl = (String) jp.get("sourceUrl");
            String author = (String) jp.get("author");
            String title = (String) jp.get("title");
            String description = (String) jp.get("description");
            String body = (String) jp.get("body");

            HashSet<Mem> mems = new HashSet<>();
            JSONArray jms = (JSONArray) jp.get("mems");
            jms.forEach(jm -> {
                Optional<Mem> m = toMem((JSONObject) jm);
                m.ifPresent(mems::add);
            });

            HashSet<Content> content = new HashSet<>();
            JSONArray jcs = (JSONArray) jp.get("content");
            jcs.forEach(jc -> {
                Optional<Content> c = toContent((JSONObject) jc);
                c.ifPresent(content::add);
            });

            res = Optional.of(new Paper(source, score.intValue(), mems, time, sourceUrl, author, title, description, body, content));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Optional<Content> toContent(JSONObject object) {

        Optional<Content> res = Optional.empty();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jc = (JSONObject) obj;

            Long type = (Long) jc.get("type");
            String url = (String) jc.get("url");

            res = Optional.of(new Content(type.intValue(), url));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Optional<Mem> toMem(JSONObject object) {

        Optional<Mem> res = Optional.empty();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jm = (JSONObject) obj;

            String name = (String) jm.get("name");
            Long cof = (Long) jm.get("popularityCoefficient");

            res = Optional.of(new Mem(name, cof.intValue()));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Optional<Preference> toPreference(JSONObject object) {

        Optional<Preference> res = Optional.empty();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jm = (JSONObject) obj;

            String name = (String) jm.get("name");
            Long val = (Long) jm.get("value");

            res = Optional.of(new Preference(name, val.intValue()));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Optional<Bubble> toBubble(JSONObject object) {

        Optional<Bubble> res = Optional.empty();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jb = (JSONObject) obj;

            JSONArray jm = (JSONArray) jb.get("mems");

            Set<Mem> mems = new HashSet<>();

            jm.forEach(jmem -> {
                Optional<Mem> m = toMem((JSONObject) jmem);
                m.ifPresent(mems::add);
            });

            res = Optional.of(new Bubble(mems));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Set<Paper> toPapers(JSONObject object) {

        Set<Paper> res = new HashSet<>();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting mems
            JSONArray jp = (JSONArray) jo.get("papers");

            jp.forEach( jpaper -> {
                Optional<Paper> p = toPaper((JSONObject) jpaper);
                p.ifPresent(res::add);
            });

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;

    }


    public static Set<Mem> toMemes(JSONObject object) {

        Set<Mem> res = new HashSet<>();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting mems
            JSONArray jm = (JSONArray) jo.get("mems");

            jm.forEach(jmem -> {
                Optional<Mem> m = toMem((JSONObject) jmem);
                m.ifPresent(res::add);
            });

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Set<Preference> toPreferences(JSONObject object) {

        HashSet<Preference> res = new HashSet<>();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting preferences
            JSONArray ja = (JSONArray) jo.get("preferences");

            // iterating preferences
            for (Object o : ja) {
                toPreference((JSONObject) o).ifPresent(res::add);
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }


    public static Set<Bubble> toBubbles(JSONObject object) {

        Set<Bubble> bubbles = new HashSet<>();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting preferences
            JSONArray ja = (JSONArray) jo.get("bubbles");

            ja.forEach(jb -> {
                Optional<Bubble> b = toBubble((JSONObject) jb);
                b.ifPresent(bubbles::add);
            });

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return bubbles;
    }

    public static Optional<Profile> toProfile(JSONObject object) {

        Optional<Profile> res = Optional.empty();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jp = (JSONObject) obj;

            Long id = (Long) jp.get("accountID");
            Long lr = (Long) jp.get("lastRequest");

            HashSet<Preference> preferences = new HashSet<>();
            HashSet<Bubble> bubbles = new HashSet<>();


            JSONArray jpr = (JSONArray) jp.get("preferences");

            jpr.forEach(pr -> toPreference((JSONObject) pr).ifPresent(preferences::add));


            JSONArray jbs = (JSONArray) jp.get("bubbles");
            jbs.forEach(b -> toBubble((JSONObject) b).ifPresent(bubbles::add));

            res = Optional.of(new Profile(id.intValue(), lr.intValue(), preferences, bubbles));

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return res;
    }

    public static Set<Profile> toProfiles(JSONObject object) {

        Set<Profile> profiles = new HashSet<>();

        try {
            Object obj = new JSONParser().parse(object.toString());

            // typecasting obj to JSONObject
            JSONObject jo = (JSONObject) obj;

            // getting preferences
            JSONArray ja = (JSONArray) jo.get("profiles");

            ja.forEach(jp -> {
                Optional<Profile> b = toProfile((JSONObject) jp);
                b.ifPresent(profiles::add);
            });

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        return profiles;
    }


    /* -------------------------- From POJO to JSON private -------------------------- */


    public static JSONArray memsToJSON(Set<Mem> mems) {
        JSONArray res = new JSONArray();
        mems.forEach(mem -> res.add(toJSON(mem)) );
        return res;
    }

    public static JSONArray bubblesToJSON(Set<Bubble> bubbles) {
        JSONArray res = new JSONArray();
        bubbles.forEach( bubble -> res.add(toJSON(bubble)) );
        return res;
    }

    public static JSONArray profilesToJSON(Set<Profile> profiles) {
        JSONArray res = new JSONArray();
        profiles.forEach(profile -> res.add(toJSON(profile)) );
        return res;
    }

    public static JSONArray preferencesToJSON(Set<Preference> preferences) {
        JSONArray res = new JSONArray();
        preferences.forEach(preference -> res.add(toJSON(preference)) );
        return res;
    }


    public static JSONObject papersToJSON(Set<Paper> data) {
        JSONObject res = new JSONObject();
        JSONArray papers = new JSONArray();

        data.forEach( p -> papers.add(toJSON(p)));
        res.put("papers", papers);

        return res;
    }

    public static JSONObject paperToJSON(Paper paper) {
        JSONObject res = new JSONObject();

        res.put("source", paper.getSource());             // source
        res.put("score", paper.getScore());               // score

        JSONArray mems = new JSONArray();
        for (Mem mem : paper.getMems())
            mems.add(toJSON(mem));
        res.put("mems", mems);                            // mems

        res.put("time", paper.getTime());                 // time
        res.put("sourceUrl", paper.getSourceUrl());       // sourceUrl
        res.put("author", paper.getAuthor());             // author
        res.put("title", paper.getTitle());               // title
        res.put("description", paper.getDescription());   // description
        res.put("body", paper.getBody());                 // body

        JSONArray contents = new JSONArray();
        for (Content content : paper.getContent()) {
            JSONObject JSONcontent = new JSONObject();
            JSONcontent.put("type", content.getType());
            JSONcontent.put("url", content.getUrl());
            contents.add(JSONcontent);
        }
        res.put("content", contents);                         // content

        return res;
    }

    /* -------------------------- From POJO to JSON public -------------------------- */

    public static JSONObject toJSON(Paper paper) {
        JSONObject res = new JSONObject();

        res.put("source", paper.getSource());             // source
        res.put("score", paper.getScore());               // score

        JSONArray mems = new JSONArray();
        for (Mem mem : paper.getMems())
            mems.add(mem.getName());
        res.put("mems", mems);                            // mems

        res.put("time", paper.getTime());                 // time
        res.put("sourceUrl", paper.getSourceUrl());       // sourceUrl
        res.put("author", paper.getAuthor());             // author
        res.put("title", paper.getTitle());               // title
        res.put("description", paper.getDescription());   // description
        res.put("body", paper.getBody());                 // body

        JSONArray contents = new JSONArray();
        for (Content content : paper.getContent()) {
            JSONObject JSONcontent = new JSONObject();
            JSONcontent.put("type", content.getType());
            JSONcontent.put("url", content.getUrl());
            contents.add(JSONcontent);
        }
        res.put("content", contents);                         // content

        return res;
    }


    public static JSONObject toJSON(Mem mem) {
        JSONObject res = new JSONObject();
        res.put("name", mem.getName());
        res.put("popularityCoefficient", mem.getPopularityCoefficient());
        return res;
    }

    public static JSONObject toJSON(Bubble bubble) {
        JSONObject res = new JSONObject();
        res.put("mems", memsToJSON(bubble.getMems()));
        return res;
    }

    public static JSONObject toJSON(Preference preference) {
        JSONObject JSONpreference = new JSONObject();
        JSONpreference.put("name", preference.getName());
        JSONpreference.put("value", preference.getValue());
        return JSONpreference;
    }

    public static JSONObject toJSON(Profile profile) {

        JSONObject JSONProfile = new JSONObject();
        JSONProfile.put("accountID", profile.getAccountID());
        JSONProfile.put("lastRequest", profile.getLastRequest());

        JSONArray jp = new JSONArray();
        profile.getPreferences().forEach(preference -> jp.add(toJSON(preference)));
        JSONProfile.put("preferences", jp);

        JSONArray jb = new JSONArray();
        profile.getBubbles().forEach(bubble -> jb.add(toJSON(bubble)));
        JSONProfile.put("bubbles", jb);

        return JSONProfile;
    }

    public static <T> JSONObject toJSON(Set<T> data, Class dataType) {
        JSONObject res = new JSONObject();

        if (dataType.equals(Profile.class))
            res.put("profiles", profilesToJSON((Set<Profile>) data));

        else if (dataType.equals(Bubble.class))
            res.put("bubbles", bubblesToJSON((Set<Bubble>) data));

        else if (dataType.equals(Mem.class))
            res.put("mems", memsToJSON((Set<Mem>) data));

        else if (dataType.equals(Preference.class))
            res.put("preferences", preferencesToJSON((Set<Preference>) data));

        else if (dataType.equals(Paper.class))
            res.put("papers", papersToJSON((Set<Paper>) data));

        return res;
    }


    /* -------------------------- TEST -------------------------- */


    public static boolean test() {

        Preference p1 = new Preference("mem1", 10);
        Preference p2 = new Preference("mem2", 20);
        Preference p3 = new Preference("mem3", 30);

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


        Profile profile = new Profile(1,
                1111,
                Set.of(p1, p2, p3),
                Set.of(bubble));


        JSONObject jmem  = toJSON(m1);
        JSONObject jpref = toJSON(Set.of(p1), Preference.class);
        JSONObject jbubb = toJSON(bubble);
        JSONObject jprof = toJSON(profile);
        JSONObject jpapr = paperToJSON(paper);

        Mem cmem         = toMem(jmem).get();
        Preference cpref = (new ArrayList<>(toPreferences(jpref))).get(0);
        Bubble cbubb     = toBubble(jbubb).get();
        Profile cprof    = toProfile(jprof).get();
        Paper cpaper     = toPaper(jpapr).get();

        return m1.equals(cmem) && p1.equals(cpref) && bubble.equals(cbubb) && profile.equals(cprof) && paper.equals(cpaper);

    }

}