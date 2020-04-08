package com.shimels.mobugs.models;

import javax.persistence.*;




@Entity
public class Bug {
    @Id
    @GeneratedValue
    private int id;

    private String  url;

    private String commonName;

    private String scientificName;

    private String[] tags;

    public Bug(){}

    public Bug(String commonName, String scientificName, String[] tags, String urk){
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.tags = tags;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }
}
