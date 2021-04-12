package com.titles.databaseservice.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;

@Entity
public class ColdPaper extends Paper {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    public ColdPaper(){
        super(false,1,new Date(),"source",2,Set.of(new Mem()),1L,"author","title","description","body","content",Set.of(new Content()));
    }
    public ColdPaper(Boolean isReaded,
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
        super(isReaded,paperID,addTime,source,score,mems,time,sourceUrl,author,title,description,body,content);
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "ColdPaper{" +
                "paperID=" + paperID +
                ", isReaded=" + isReaded +
                ", addTime=" + addTime +
                ", source='" + source + '\'' +
                ", score=" + score +
                ", mems=" + mems +
                ", time=" + time +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", body='" + body + '\'' +
                ", content=" + content +
                ", id=" + id +
                '}';
    }

    public Paper toPaper()
    {
        return new Paper(isReaded,paperID,addTime,source,score,mems,time,sourceUrl,author,title,description,body,content);
    }
}
