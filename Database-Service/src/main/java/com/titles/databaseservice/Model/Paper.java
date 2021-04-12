package com.titles.databaseservice.Model;

import com.titles.databaseservice.Model.ColdPaper;
import com.titles.databaseservice.Model.Content;
import com.titles.databaseservice.Model.HotPaper;
import com.titles.databaseservice.Model.Mem;

import java.util.Date;
import java.util.Set;

public class Paper {
    // DB internal data
    protected Integer paperID;
    protected Boolean isReaded;
    protected Date addTime;

    // Data for logic
    protected String source;
    protected Integer score;
    protected Set<Mem> mems;
    protected Long time;

    // Data for accessing
    protected String sourceUrl;

    // Data for output
    protected String author;
    protected String title;
    protected String description;
    protected String body;
    protected Set<Content> content;


    public Paper(Boolean isReaded,
                 Integer paperID,
                 Date addTime,
                 String source,
                 Integer score,
                 Set<Mem> mems,
                 Long time,
                 String sourceUrl,
                 String author,
                 String title,
                 String description,
                 String body,
                 Set<Content> content)
    {
        this.paperID = paperID;
        this.isReaded = isReaded;
        this.addTime = new Date();
        this.source = source;
        this.score = score;
        this.mems = mems;
        this.time = time;
        this.sourceUrl = sourceUrl;
        this.author = author;
        this.title = title;
        this.description = description;
        this.body = body;
        this.content = content;
    }

    public Paper(String source,
                 Integer score,
                 Set<Mem> mems,
                 Long time,
                 String sourceUrl,
                 String author,
                 String title,
                 String description,
                 String body,
                 Set<Content> content)
    {
        this.source = source;
        this.score = score;
        this.mems = mems;
        this.time = time;
        this.sourceUrl = sourceUrl;
        this.author = author;
        this.title = title;
        this.description = description;
        this.body = body;
        this.content = content;
        this.isReaded = false;
        this.paperID = null;
        this.addTime = new Date();
    }

    public void setPaperID(Integer paperID) {
        this.paperID = paperID;
    }

    public void setReaded(Boolean readed) {
        isReaded = readed;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setMems(Set<Mem> mems) {
        this.mems = mems;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setContent(Set<Content> content) {
        this.content = content;
    }

    public Integer getPaperID() { return paperID; }

    public Boolean getReaded() { return isReaded; }

    public Date getAddTime() { return addTime; }

    public String getSource() {
        return source;
    }

    public Integer getScore() {
        return score;
    }

    public Set<Mem> getMems() {
        return mems;
    }

    public Long getTime() {
        return time;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public Set<Content> getContent() {
        return content;
    }



    public ColdPaper toCold(){
        return new ColdPaper(isReaded,
                 paperID,
                 addTime,
                 source,
                 score,
                 mems,
                 time,
                 sourceUrl,
                 author,
                 title,
                 description,
                 body,
                 content);
    }
    public HotPaper toHot(){
        return new HotPaper(isReaded,
                paperID,
                addTime,
                source,
                score,
                mems,
                time,
                sourceUrl,
                author,
                title,
                description,
                body,
                content);
    }


//    @Override
//    public String toString() {
//        return "Paper{" +
//                ", source='" + source + '\'' +
//                ", id=" + id +
//                ", author='" + author + '\'' +
//                ", time=" + time +
//                ", score=" + score +
//                ", tags=" + tags +
//                ", theme=" + theme +
//                ", url='" + url + '\'' +
//                ", title='" + title + '\'' +
//                ", description='" + description + '\'' +
//                ", text='" + text + '\'' +
//                ", content=" + content +
//                '}';
//    }
}
