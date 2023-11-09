package itmo.corp.secondaryservice.controller;

import itmo.corp.secondaryservice.dto.Error;
import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import itmo.corp.secondaryservice.exception.ClientException;
import itmo.corp.secondaryservice.service.StarshipService;
import jakarta.inject.Inject;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;

@Path("/starships")
public class StarshipController {

    @Inject
    private StarshipService starshipService;

    @PUT
    @Path("/{starship_id}/load/{space-marine_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response loadSpaceMarine(
            @HeaderParam("Authorization") String token,
            @PathParam("starship_id") String starshipId,
            @PathParam("space-marine_id") String spaceMarineId
    ) {
        long starshipIdLong;
        long spaceMarineIdLong;
        try {
            starshipIdLong = Long.parseLong(starshipId);
            spaceMarineIdLong = Long.parseLong(spaceMarineId);
        } catch (NumberFormatException ex) {
            return Response
                    .status(400).entity(Error.builder()
                            .code(400)
                            .message("Некорректные параметры запроса")
                            .timestamp(Instant.now()).build())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }

        try {
            SpaceMarineResponseDto responseDto = starshipService.loadToStarship(starshipIdLong, spaceMarineIdLong, token);
            return Response.status(200).entity(responseDto).type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (ClientException e) {
            return Response
                    .status(e.getError().getCode())
                    .entity(e.getError())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }
    }

    @PUT
    @Path("/{starship_id}/unload-all")
    @Produces(MediaType.TEXT_PLAIN)
    public Response unloadAllSpaceMarines(
            @HeaderParam("Authorization") String token,
            @PathParam("starship_id") String starshipId
    ) {
        long starshipIdLong;
        try {
            starshipIdLong = Long.parseLong(starshipId);
        } catch (NumberFormatException ex) {
            return Response
                    .status(400).entity(Error.builder()
                            .code(400)
                            .message("Некорректные параметры запроса")
                            .timestamp(Instant.now()).build())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }

        try {
            starshipService.unloadAllFromStarship(starshipIdLong, token);
            return Response.status(204).build();
        } catch (ClientException e) {
            return Response
                    .status(e.getError().getCode())
                    .entity(e.getError())
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }
    }
}
