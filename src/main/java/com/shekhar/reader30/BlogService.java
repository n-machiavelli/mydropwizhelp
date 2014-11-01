package com.shekhar.reader30;

/**
 * Created by sys_buajoku on 9/8/2014.
 */
import com.mongodb.DB;
import com.shekhar.reader30.representations.Blog;
import com.shekhar.reader30.resources.BlogResource;
import com.shekhar.reader30.resources.IndexResource;
import com.mongodb.Mongo;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import net.vz.mongodb.jackson.JacksonDBCollection;

public class BlogService extends Service<BlogConfiguration> {

    public static void main(String[] args) throws Exception {
        new BlogService().run(new String[] { "server" });
    }

    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
        bootstrap.setName("blog");
    }

    @Override
    public void run(BlogConfiguration configuration, Environment environment) throws Exception {
        Mongo mongo = new Mongo(configuration.mongohost, configuration.mongoport);
        MongoManaged mongoManaged = new MongoManaged(mongo);
        environment.manage(mongoManaged);

        environment.addHealthCheck(new MongoHealthCheck(mongo));
        //this block was added to update BlogService run method to add BlogResource as well
        //we use db here bcos IndexResource at this time was plain text/json...
        DB db = mongo.getDB(configuration.mongodb);
        JacksonDBCollection<Blog, String> blogs = JacksonDBCollection.wrap(db.getCollection("blogs"), Blog.class, String.class);

        environment.addResource(new IndexResource(blogs));

        environment.addResource(new BlogResource(blogs));
        //end of new block
        //environment.addResource(new IndexResource());
    }
}
/*

The class has a main method which acts as our service entry point. Inside the main method, we create an instance of BlogService
 and call the run method. We pass the server command as argument. The server command will start the embedded jetty server.

The initialize method is called before running the service run method. We set the name of service to blog.

Next, we have the run method which will be called when service runs. Later  We will add JAX-RS resources inside this method.
 */
//Now we will update the BlogService run method to add BlogResource as well. Note that Indexresource is hardcoded