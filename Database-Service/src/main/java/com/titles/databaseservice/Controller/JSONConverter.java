package com.titles.databaseservice.Controller;

import com.titles.databaseservice.Model.Content;
import com.titles.databaseservice.Model.Mem;
import com.titles.databaseservice.Model.Paper;
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


    /* -------------------------- From POJO to JSON private -------------------------- */


    public static JSONArray memsToJSON(Set<Mem> mems) {
        JSONArray res = new JSONArray();
        mems.forEach(mem -> res.add(toJSON(mem)) );
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

    public static <T> JSONObject toJSON(Set<T> data, Class dataType) {
        JSONObject res = new JSONObject();

        if (dataType.equals(Mem.class))
            res.put("mems", memsToJSON((Set<Mem>) data));
        else if (dataType.equals(Paper.class))
            res.put("papers", papersToJSON((Set<Paper>) data));

        return res;
    }


    /* -------------------------- TEST -------------------------- */


    public static boolean test() {

        Mem m1 = new Mem("mem1", 1);
        Mem m2 = new Mem("mem2", 2);

        HashSet<Mem> mems1  = new HashSet<>(List.of(m1, m2));

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


        JSONObject jmem  = toJSON(m1);
        JSONObject jpapr = paperToJSON(paper);

        Mem cmem         = toMem(jmem).get();
        Paper cpaper     = toPaper(jpapr).get();

        return m1.equals(cmem)&& paper.equals(cpaper);

    }

}