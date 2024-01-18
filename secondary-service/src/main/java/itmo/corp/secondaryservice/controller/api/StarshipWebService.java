package itmo.corp.secondaryservice.controller.api;


import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService
public interface StarshipWebService {

    @WebMethod
    String processStrings(
            @WebParam(name = "param1") String param1,
            @WebParam(name = "param2") String param2
    );

    @WebMethod
    String sayHello();

    @WebMethod
    SpaceMarineResponseDto loadSpaceMarine(
            @WebParam(name = "starshipId") String starshipId,
            @WebParam(name = "spaceMarineId") String spaceMarineId
    );
}
