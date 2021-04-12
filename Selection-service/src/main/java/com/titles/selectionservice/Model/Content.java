package com.titles.selectionservice.Model;

import java.util.Objects;

public class Content {

    private final Integer type;
    private final String url;

    public Content(Integer type, String url) {
        this.type = type;
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return type.equals(content.type) && url.equals(content.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, url);
    }

    @Override
    public String toString() {
        return "Content{" +
                "type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
