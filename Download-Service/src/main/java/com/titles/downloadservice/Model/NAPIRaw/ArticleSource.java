package com.titles.downloadservice.Model.NAPIRaw;

import java.io.Serializable;
import java.util.Optional;

/*
: {
        "id": "cnn",
        "name": "CNN"
      },
 */
public class ArticleSource implements Serializable {
    private Optional<String> id;
    private Optional<String> name;

    public Optional<String> getId() {
        return id;
    }

    public void setId(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> getName() {
        return name;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ArticleSource{" +
                "id=" + id.orElse("") +
                ", name=" + name.orElse("") +
                '}';
    }
}
