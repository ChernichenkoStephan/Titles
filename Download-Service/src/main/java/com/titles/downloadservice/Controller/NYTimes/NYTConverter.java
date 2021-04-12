package com.titles.downloadservice.Controller.NYTimes;

import com.titles.downloadservice.Model.NYTRaw.NYTArticle;
import com.titles.downloadservice.Model.NYTRaw.NYTMedia;
import com.titles.downloadservice.Model.NYTRaw.NYTMediaData;
import com.titles.downloadservice.Model.NYTRaw.NYTRawData;
import com.titles.downloadservice.Model.Paper.Content;
import com.titles.downloadservice.Model.Paper.Mem;
import com.titles.downloadservice.Model.Paper.Paper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NYTConverter {

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
    public static List<Paper> converter(NYTRawData rawData) {

        ArrayList<Paper> res = new ArrayList<>();
        if (rawData.getResults().isEmpty()) { return List.of(); }

        for (NYTArticle result : rawData.getResults().get()) {
            res.add(toPaper(result));
        }

        return res;
    }

    /**
     *
     * @param result
     * @return
     */
    private static Paper toPaper(NYTArticle result) {

        String source = result.getSource().orElse("");
        String author = result.getByline().orElse("");

        // TODO: Добавить конвертацию времени
        Long time = -1L;
        if (result.getUpdated().isPresent()) {
            time = timeParser(result.getUpdated().get());
        } else if (result.getPublished_date().isPresent()) {
            time = timeParser(result.getPublished_date().get());
        }

        // TODO: Добавить базовое значение
        Integer score = -1;

        HashSet<Mem> mems = new HashSet<>();

        mems.add(new Mem("NYTimes", 0));

        if (result.getSubsection().isPresent())
            mems.add(new Mem(result.getSubsection().get(),0));

        if (result.getAdx_keywords().isPresent())
            Arrays.asList(result.getAdx_keywords().get().split(";")).forEach(mem -> mems.add(new Mem(mem, 0)));

        if (result.getDes_facet().isPresent())
            result.getDes_facet().get().forEach(mem -> mems.add(new Mem(mem, 0)));

        if (result.getOrg_facet().isPresent())
            result.getOrg_facet().get().forEach(mem -> mems.add(new Mem(mem, 0)));

        if (result.getPer_facet().isPresent())
            result.getPer_facet().get().forEach(mem -> mems.add(new Mem(mem, 0)));

        if (result.getGeo_facet().isPresent())
            result.getPer_facet().get().forEach(mem -> mems.add(new Mem(mem, 0)));


        String url = result.getUrl().orElse("");
        String title = result.getTitle().orElse("");
        String description = result.get_abstract().get();

        String text = "";

        HashSet<Content> content = new HashSet<>();
        if (result.getMedia().isPresent()) {
            for (NYTMedia media : result.getMedia().get()) {
                int type = -1;
                String mediaUrl = "";

                if (media.getType().orElse("").equals("image")) {
                    type = 1;
                } else if (media.getType().orElse("").equals("audio")) {
                    type = 2;
                }

                if (media.getMedia_metadata().isPresent()) {
                    Integer maxArea = 0;
                    NYTMediaData mediaBody = media.getMedia_metadata().get().stream().max( (m1, m2) -> {
                        Integer area1 = m1.getHeight().orElse(0) * m1.getWidth().orElse(0);
                        Integer area2 = m2.getHeight().orElse(0) * m2.getWidth().orElse(0);
                        return area1 - area2;
                        }
                    ).get();

                    mediaUrl = mediaBody.getUrl().orElse("");

                }

                if (media.getCopyright().isPresent())
                    mems.add(new Mem(media.getCopyright().get(), 0));

                 content.add(new Content(type, mediaUrl));
            }
        }

        return new Paper(source, score, mems, time, url, author, title, description, text, content);
    }

}
