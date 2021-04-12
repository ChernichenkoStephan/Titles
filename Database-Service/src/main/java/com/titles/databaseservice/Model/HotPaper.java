package com.titles.databaseservice.Model;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Set;
@Entity
public class HotPaper extends Paper {
    @Id
    public String id;
    public Integer hpID;
    // DB internal data
    public HotPaper()
    {
        super(true,1,new Date(),"source",2,Set.of(new Mem()),1L,"author","title","description","body","content",Set.of(new Content()));
        this.id = "id";
    }
    public HotPaper( Boolean isReaded,
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

    @Override
    public String toString() {
        return "HotPaper{" +
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
                ", id='" + id + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHpID(Integer hpID) {
        this.hpID = hpID;
    }

    public String getId() {
        return id;
    }

    public Integer getHpID() {
        return hpID;
    }

    public Paper toPaper()
    {
        return new Paper(isReaded,paperID,addTime,source,score,mems,time,sourceUrl,author,title,description,body,content);
    }
}
