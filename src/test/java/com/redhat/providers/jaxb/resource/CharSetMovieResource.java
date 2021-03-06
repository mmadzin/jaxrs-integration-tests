package com.redhat.providers.jaxb.resource;

import org.jboss.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.nio.charset.Charset;

@Path("/")
public class CharSetMovieResource {

    private final Logger log = Logger.getLogger(CharSetMovieResource.class.getName());

    @GET
    @Path("junk")
    public String junk() {
        return "junk";
    }

    @POST
    @Path("xml/produces")
    @Consumes("application/xml")
    public CharSetFavoriteMovieXmlRootElement xmlProduces(CharSetFavoriteMovieXmlRootElement movie) {
        log.info("server default charset: " + Charset.defaultCharset());
        log.info("title: " + movie.getTitle());
        return movie;
    }

    @POST
    @Path("xml/accepts")
    @Consumes("application/xml")
    public CharSetFavoriteMovieXmlRootElement xmlAccepts(CharSetFavoriteMovieXmlRootElement movie) {
        log.info("server default charset: " + Charset.defaultCharset());
        log.info("title: " + movie.getTitle());
        return movie;
    }

    @POST
    @Path("xml/default")
    @Consumes("application/xml")
    @Produces("application/xml")
    public CharSetFavoriteMovieXmlRootElement xmlDefault(CharSetFavoriteMovieXmlRootElement movie) {
        log.info("server default charset: " + Charset.defaultCharset());
        log.info("title: " + movie.getTitle());
        return movie;
    }
}
