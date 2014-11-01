package com.shekhar.reader30;

/**
 * Created by sys_buajoku on 9/8/2014.
 */
import com.mongodb.Mongo;
import com.yammer.dropwizard.lifecycle.Managed;

public class MongoManaged implements Managed {

    private Mongo mongo;

    public MongoManaged(Mongo mongo) {
        this.mongo = mongo;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongo.close();
    }

}