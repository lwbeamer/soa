package itmo.corp.secondaryservice.controller;

import itmo.corp.secondaryservice.controller.api.StarshipWebService;
import itmo.corp.secondaryservice.dto.AstartesCategory;
import itmo.corp.secondaryservice.dto.Chapter;
import itmo.corp.secondaryservice.dto.Coordinates;
import itmo.corp.secondaryservice.dto.Error;
import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import itmo.corp.secondaryservice.dto.Weapon;
import itmo.corp.secondaryservice.exception.ClientException;
import itmo.corp.secondaryservice.service.StarshipService;
import jakarta.inject.Inject;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.WebServiceException;

import java.time.Instant;
import java.time.LocalDate;

@WebService(endpointInterface = "itmo.corp.secondaryservice.controller.api.StarshipWebService")
public class StarshipWebServiceImpl implements StarshipWebService{

    @Inject
    private StarshipService starshipService;

//    @PUT
//    @Path("/{starship_id}/load/{space-marine_id}")
//    @Produces(MediaType.TEXT_PLAIN)
//    @WebMethod
//    public Response loadSpaceMarine(
//            @WebParam(name = "starshipId") String starshipId,
//            @WebParam(name = "spaceMarineId") String spaceMarineId
//    ) {
//        String token = "dummy";
//        System.out.println("ЗАШЛИ В КОНТРОЛЛЕР!");
//        long starshipIdLong;
//        long spaceMarineIdLong;
//        try {
//            starshipIdLong = Long.parseLong(starshipId);
//            spaceMarineIdLong = Long.parseLong(spaceMarineId);
//        } catch (NumberFormatException ex) {
//            return Response
//                    .status(400).entity(Error.builder()
//                            .code(400)
//                            .message("Некорректные параметры запроса")
//                            .timestamp(Instant.now()).build())
//                    .type(MediaType.APPLICATION_JSON_TYPE)
//                    .build();
//        }
//
//        try {
//            SpaceMarineResponseDto responseDto = starshipService.loadToStarship(starshipIdLong, spaceMarineIdLong, token);
//            return Response.status(200).entity(responseDto).type(MediaType.APPLICATION_JSON_TYPE).build();
//        } catch (ClientException e) {
//            return Response
//                    .status(e.getError().getCode())
//                    .entity(e.getError())
//                    .type(MediaType.APPLICATION_JSON_TYPE)
//                    .build();
//        }
//    }





    @Override
    public String sayHello() {
        return "HELLO !";
    }

    @Override
    public SpaceMarineResponseDto loadSpaceMarine(String starshipId, String spaceMarineId) {
        try {
            System.out.println(starshipId + " ||||| " + spaceMarineId);
            Long parsedStarshipId = Long.parseLong(starshipId);
            Long parsedSpaceMarineId = Long.parseLong(spaceMarineId);

            Coordinates cord = new Coordinates();
            cord.setX(228L);
            cord.setY(228L);

            Chapter chapter = new Chapter();
            chapter.setName("GLAVA");
            chapter.setWorld("MIR");

            SpaceMarineResponseDto responseDto = SpaceMarineResponseDto.builder()
                    .id(parsedSpaceMarineId)
                    .name("some name")
                    .coordinates(cord)
                    .health(100L)
                    .creationDate(LocalDate.now())
                    .achievements("achievements")
                    .category(AstartesCategory.LIBRARIAN)
                    .weaponType(Weapon.BOLTGUN)
                    .chapter(chapter)
                    .starshipId(parsedStarshipId)
                    .build();

            return responseDto;
        } catch (NumberFormatException e) {
            throw new WebServiceException("Incorrect fucking request!");
        }
    }

    @Override
    public String processStrings(String param1, String param2) {
        String result = "Processed: " + param1 + ", " + param2;
        return result;
    }

//    @PUT
//    @Path("/{starship_id}/unload-all")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response unloadAllSpaceMarines(
//            @HeaderParam("Authorization") String token,
//            @PathParam("starship_id") String starshipId
//    ) {
//
//        long starshipIdLong;
//        try {
//            starshipIdLong = Long.parseLong(starshipId);
//        } catch (NumberFormatException ex) {
//            return Response
//                    .status(400).entity(Error.builder()
//                            .code(400)
//                            .message("Некорректные параметры запроса")
//                            .timestamp(Instant.now()).build())
//                    .type(MediaType.APPLICATION_JSON_TYPE)
//                    .build();
//        }
//
//        try {
//            starshipService.unloadAllFromStarship(starshipIdLong, token);
//            return Response.status(204).build();
//        } catch (ClientException e) {
//            return Response
//                    .status(e.getError().getCode())
//                    .entity(e.getError())
//                    .type(MediaType.APPLICATION_JSON_TYPE)
//                    .build();
//        }
//    }
}
