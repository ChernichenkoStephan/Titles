package com.titles.downloadservice.Controller;


import com.titles.downloadservice.Model.Paper.Content;
import com.titles.downloadservice.Model.Paper.Mem;
import com.titles.downloadservice.Model.Paper.Paper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.*;

public class JSONConverter {


    private static JSONArray memsToJSON(Set<Mem> mems) {
        JSONArray res = new JSONArray();
        mems.forEach(mem -> res.add(toJSON(mem)) );
        return res;
    }

    public static JSONObject toJSON(List<Paper> data) {
        JSONObject res = new JSONObject();
        JSONArray papers = new JSONArray();

        data.forEach( p -> papers.add(toJSON(p)));
        res.put("papers", papers);

        return res;
    }

    private static JSONObject toJSON(Mem mem) {
        JSONObject res = new JSONObject();
        res.put("name", mem.getName());
        res.put("popularityCoefficient", mem.getPopularityCoefficient());
        return res;
    }

    private static JSONObject toJSON(Paper paper) {
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




}
