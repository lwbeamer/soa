package itmo.corp.secondaryservice.controller;

import itmo.corp.secondaryservice.controller.api.StarshipWebService;
import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import itmo.corp.secondaryservice.exception.ClientException;
import itmo.corp.secondaryservice.service.StarshipService;
import jakarta.inject.Inject;
import jakarta.jws.WebService;
import jakarta.xml.ws.WebServiceException;


@WebService(endpointInterface = "itmo.corp.secondaryservice.controller.api.StarshipWebService")
public class StarshipWebServiceImpl implements StarshipWebService{

    @Inject
    private StarshipService starshipService;


    @Override
    public String sayHello() {
        return "HELLO !";
    }

    @Override
    public SpaceMarineResponseDto loadSpaceMarine(String starshipId, String spaceMarineId) {
        long starshipIdLong;
        long spaceMarineIdLong;
        String token = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJsd2JlYW1lciIsImlhdCI6MTY5ODA5Mzk5M30.0-GdiYjVCjnS4bMQL0nGWPmiG4QarpG-4MpfjFBxURdT9j9r2mAYXhLDcoE1RAhNTuvqHVhVdIy0BHJoeCWCo4w4TRJ6Wj4M7p-gLgTeZ9chS13_aa76ft3IyxWuoyoeqjBNuwUmBOfGqejW8xu87hSow50CJAbD2hCw34nEs2vTog2VTOPupd7ABjLksEX-_KwTFmJGvtUREYpRbUEdOE-gm-8AwIJ4LQyLi3dCE9RNuKgABdtJpjNCD0MDwZjRcLTm91WG2QNApJRA6MqxwZUjBWLbmhxbBHmvkD5SuD6Gyer-4ftuSWXeF01VHFfxJes4wm8E4dBZFif1RLufTA";
        try {
             starshipIdLong = Long.parseLong(starshipId);
             spaceMarineIdLong = Long.parseLong(spaceMarineId);
        } catch (NumberFormatException e) {
            throw new WebServiceException("Incorrect request!");
        }
        try {
            return starshipService.loadToStarship(starshipIdLong, spaceMarineIdLong, token);
        } catch (ClientException e) {
            throw new WebServiceException(e.getMessage());
        }
    }

    @Override
    public String processStrings(String param1, String param2) {
        return "Processed: " + param1 + ", " + param2;
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
