package itmo.corp.secondaryservice.controller;

import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/starships")
public class StarshipController {

    @PUT
    @Path("/{starship_id}/load/{space-marine_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loadSpaceMarine(
            @PathParam("starship_id") String starshipId,
            @PathParam("space-marine_id") String spaceMarineId
    ) {
        return Response.ok("Space marine loaded successfully" + starshipId + " " + spaceMarineId).build();
    }

    @PUT
    @Path("/{starship_id}/unload-all")
    @Produces(MediaType.TEXT_PLAIN)
    public Response unloadAllSpaceMarines(
            @PathParam("starship_id") String starshipId
    ) {
        return Response.ok("All space marines unloaded successfully" + starshipId).build();
    }
}
