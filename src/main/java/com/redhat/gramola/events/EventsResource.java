package com.redhat.gramola.events;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.redhat.gramola.events.beans.Event;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventsResource {
    Logger logger = Logger.getLogger(EventsResource.class);

    @ConfigProperty(name = "gramola.welcome-message", defaultValue = "Welcome")
    String welcome;

    @ConfigProperty(name = "info.name", defaultValue = "gramola-events")
    String infoName;

    @GET
    @Path("info/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String infoName() {
        return infoName;
    }

    @GET
    @Path("welcome")
    @Produces(MediaType.TEXT_PLAIN)
    public String welcome() {
        logger.debug("welcome method is called");
        return welcome;
    }

    @GET
    @Path("")
    public List<Event> allEvents() {
        return Event.listAll(); 
    }

    @POST
    @Path("")
    @Transactional
    public Response saveEvent(Event Event) {
        // since the EventEntity is a panache entity
        // persist is available by default
        Event.persist();
        final URI createdUri = UriBuilder.fromResource(EventsResource.class)
                        .path(Long.toString(Event.id))
                        .build();
        return Response.created(createdUri).build();
    }

    @GET
    @Path("{country}/{city}")
    public List<Event> eventsByCountryAndCity(@PathParam("country") String country, @PathParam("city") String city) {
        return Event.findEventsByCountryAndCity(country, city);
    }
}