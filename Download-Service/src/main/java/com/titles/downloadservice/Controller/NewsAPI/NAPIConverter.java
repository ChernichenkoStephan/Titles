package com.titles.downloadservice.Controller.NewsAPI;

import com.titles.downloadservice.Model.NAPIRaw.Article;
import com.titles.downloadservice.Model.Paper.Content;
import com.titles.downloadservice.Model.NAPIRaw.NewsAPIRawData;
import com.titles.downloadservice.Model.Paper.Mem;
import com.titles.downloadservice.Model.Paper.Paper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NAPIConverter {

    /**
     * Method for time parsing
     * @param strTime
     * @return
     */
    private static Long timeParser(String strTime) {
        return 1111L;
    }

    /**
     * Method that converts raw data, to Paper
     * @param rawData
     * @return
     */
    public static List<Paper> converter(NewsAPIRawData rawData) {
        ArrayList<Paper> res = new ArrayList<>();
        if (rawData.getArticles().isEmpty()) { return List.of(); }

        for (Article article : rawData.getArticles().get()) {
            res.add(toPaper(article));
        }

        return res;
    }

    /**
     *  Method that converts raw data, to Paper
     * @param article
     * @return
     */
    private static Paper toPaper(Article article) {
        String source = "NewsAPI";
        if (article.getSource().isPresent() && article.getSource().get().getName().isPresent())
            source = article.getSource().get().getName().get();

        String author = "";
        if (article.getAuthor().isPresent())
            author = article.getAuthor().get();

        // TODO: Добавить конвертацию времени
        Long time = -1L;
        if (article.getPublishedAt().isPresent())
            time = timeParser(article.getPublishedAt().get());

        Integer score = -1;

        HashSet<Mem> mems = new HashSet<>();
        mems.add(new Mem(source, 0));
        mems.add(new Mem(author, 0));

        String url = "";
        if (article.getUrl().isPresent())
            url = article.getUrl().get();

        String title = "";
        if (article.getTitle().isPresent())
            title = article.getTitle().get();

        String description = "";
        if (article.getDescription().isPresent())
            description = article.getDescription().get();

        String text = "";
        if (article.getContent().isPresent())
            text = article.getContent().get();

        Integer type = 1;

        String contentUrl = "";
        if (article.getUrlToImage().isPresent())
            contentUrl = article.getUrlToImage().get();

        Set<Content> content = Set.of(new Content(type, contentUrl));

        return new Paper(source, score, mems, time, url, author, title, description, text, content);
    }


}
