package com.example.rest;

import com.example.client.QuotesClient;
import com.example.rest.exception.ExternalException;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 * @author jure.repe
 */
@Slf4j
@Path("/quotes")
@ApplicationScoped
public class QuotesResource {

    @RestClient
    QuotesClient quotesClient;

    @GET
    @Path("/today")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getQuoteOfTheDay() {
        log.info("Fetching Quote of the Day from external service");
        return Response.ok().entity(quotesClient.getQuoteOfTheDay().get(0)).build();
    }

    @GET
    @Path("/random")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getRandomQuote() {
        log.info("Fetching Random Quote from external service");
        return Response.ok().entity(quotesClient.getRandomQuote().get(0)).build();
    }

    @GET
    @Path("/error")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getNothingButErrors() {
        log.info("Fetching more Amazing Quote's from external service");
        throw new ExternalException("Oh noes! I have an internal error!");
    }
}
