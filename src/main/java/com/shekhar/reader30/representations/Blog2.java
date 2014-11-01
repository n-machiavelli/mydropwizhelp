package com.shekhar.reader30.representations;

/**
 * Created by sys_buajoku on 9/9/2014.
 */
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import net.vz.mongodb.jackson.Id;
import net.vz.mongodb.jackson.ObjectId;

public class Blog2 {

    @Id
    @ObjectId
    
    private String id;

    @NotBlank
    private String title;

    @URL
    @NotBlank
    private String url;

    private final Date publishedOn = new Date();

    public Blog2() {
    }

    public Blog2(String id,String title, String url) {
        super();
        this.id=id;
        this.title = title;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Date getPublishedOn() {
        return publishedOn;
    }
}

