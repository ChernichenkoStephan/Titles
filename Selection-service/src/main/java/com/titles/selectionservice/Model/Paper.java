package com.titles.selectionservice.Model;
import java.util.Objects;
import java.util.Set;

public class Paper {

    // Data for logic
    private final String source;
    private Integer score;
    private final Set<Mem> mems;
    private final Long time;

    // Data for accessing
    private final String sourceUrl;

    // Data for output
    private final String author;
    private final String title;
    private final String description;
    private final String body;
    private final Set<Content> content;

    public Paper(String source, Integer score, Set<Mem> mems, Long time, String sourceUrl, String author, String title, String description, String body, Set<Content> content) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paper paper = (Paper) o;
        return Objects.equals(source, paper.source) && Objects.equals(score, paper.score) && Objects.equals(mems, paper.mems) && Objects.equals(time, paper.time) && Objects.equals(sourceUrl, paper.sourceUrl) && Objects.equals(author, paper.author) && Objects.equals(title, paper.title) && Objects.equals(description, paper.description) && Objects.equals(body, paper.body) && Objects.equals(content, paper.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, score, mems, time, sourceUrl, author, title, description, body, content);
    }

    @Override
    public String toString() {
        return "Paper{" +
                "\nsource='" + source + '\'' +
                ", \nscore=" + score +
                ", \nmems=" + mems +
                ", \ntime=" + time +
                ", \nsourceUrl='" + sourceUrl + '\'' +
                ", \nauthor='" + author + '\'' +
                ", \ntitle='" + title + '\'' +
                ", \ndescription='" + description + '\'' +
                ", \nbody='" + body + '\'' +
                ", \ncontent=" + content +
                '}';
    }

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

    public void setScore(Integer score) { this.score  = score; }
}

