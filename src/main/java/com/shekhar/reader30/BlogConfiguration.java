package com.shekhar.reader30;

/**
 * Created by sys_buajoku on 9/8/2014.
 */
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import com.yammer.dropwizard.config.Configuration;
//ran mvn install on the project source then closed and reopened before yammer dependency was sorted
public class BlogConfiguration extends Configuration {


    @JsonProperty
    @NotEmpty
    public String mongohost = "localhost";

    @JsonProperty
    @Min(1)
    @Max(65535)
    public int mongoport = 27017;

    @JsonProperty
    @NotEmpty
    public String mongodb = "mydb";
}
