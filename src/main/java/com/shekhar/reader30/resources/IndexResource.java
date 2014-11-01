package com.shekhar.reader30.resources;

/**
 * Created by sys_buajoku on 9/8/2014.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.shekhar.reader30.representations.Blog;

import com.yammer.metrics.annotation.Timed;
import net.vz.mongodb.jackson.DBCursor;
import net.vz.mongodb.jackson.JacksonDBCollection;

@Path("/")
public class IndexResource {

    private JacksonDBCollection<Blog, String> collection;

    public IndexResource(JacksonDBCollection<Blog, String> blogs) {
        this.collection = blogs;
    }

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    @Timed
    public List<Blog> index() {
        /* return Arrays.asList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
                */
        DBCursor<Blog> dbCursor = collection.find();
        List<Blog> blogs = new ArrayList<>();
        while (dbCursor.hasNext()) {
            Blog blog = dbCursor.next();
            blogs.add(blog);
        }
        return blogs;
    }

}
/*
standard JAX-RS resource class. It is annotated with @Path annotation and defines index() method.
The index() returns a collection of blogs. These blogs will be converted to JSON document.
The @Timed annotation makes sure that Dropwizard infrastructure time this method.
 */