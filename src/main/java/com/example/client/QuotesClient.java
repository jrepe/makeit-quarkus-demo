package com.example.client;

import com.example.entity.Quote;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 * @author jure.repe
 */
@Path("/api/")
@RegisterRestClient(baseUri = "https://zenquotes.io/")
public interface QuotesClient {

    @GET
    @Path("/today")
    List<Quote> getQuoteOfTheDay();

    @GET
    @Path("/random")
    List<Quote> getRandomQuote();
}
