package com.titles.downloadservice.Controller.HackerNews;

import com.titles.downloadservice.Model.HackerRaw.HackerHit;
import com.titles.downloadservice.Model.HackerRaw.HackerRawData;
import com.titles.downloadservice.Model.Paper.Content;
import com.titles.downloadservice.Model.Paper.Mem;
import com.titles.downloadservice.Model.Paper.Paper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HackerConverter {

    /**
     *
     * @param strTime
     * @return
     */
    private static Long timeParser(String strTime) {
        return 1111L;
    }

    /**
     *
     * @param rawData
     * @return
     */
    public static List<Paper> converter(HackerRawData rawData) {

        ArrayList<Paper> res = new ArrayList<>();
        if (rawData.getHits().isEmpty()) { return List.of(); }

        for (HackerHit hit : rawData.getHits().get()) {
            res.add(toPaper(hit));
        }

        return res;
    }

    /**
     *
     * @param hit
     * @return
     */
    private static Paper toPaper(HackerHit hit) {

        String source = "HackerNews";

        String author = "";
        if (hit.getAuthor().isPresent())
            author = hit.getAuthor().get();

        // TODO: Добавить конвертацию времени
        Long time = -1L;
        if (hit.getCreated_at().isPresent())
            time = timeParser(hit.getCreated_at().get());

        Integer score = -1;
        if (hit.getPoints().isPresent()) {
            score = hit.getPoints().get();
            if (hit.getNum_comments().isPresent()) {
                double increase =  Double.valueOf(hit.getNum_comments().get()) / 100;
                score += (int) increase;
            }
        }

        HashSet<Mem> mems = new HashSet<>();
        mems.add(new Mem(source, 0));
        mems.add(new Mem(author, 0));

        String url = "";
        if (hit.getUrl().isPresent())
            url = hit.getUrl().get();

        String title = "";
        if (hit.getTitle().isPresent())
            title = hit.getTitle().get();

        String description = "";
        if (hit.get_highlightResult().isPresent())
            if (hit.get_highlightResult().get().getTitle().isPresent())
                if (hit.get_highlightResult().get().getTitle().get().getValue().isPresent())
                    description = hit.get_highlightResult().get().getTitle().get().getValue().get();

        String text = "";

        Set<Content> content = Set.of();

        return new Paper(source, score, mems, time, url, author, title, description, text, content);
    }


}
