package itmo.corp.secondaryservice.client;

import itmo.corp.secondaryservice.dto.AuthenticationRequestDto;
import itmo.corp.secondaryservice.dto.AuthenticationResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class StarshipRestClient {
    private static final String API_URL = "http://localhost:9999/api/auth/login";

    public AuthenticationResponseDto sendPostRequest(AuthenticationRequestDto requestData) {
        Client client = ClientBuilder.newClient();

        Response response = client
                .target(API_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(requestData, MediaType.APPLICATION_JSON));

        AuthenticationResponseDto responseData = null;

        if (response.getStatus() == 200) {
            responseData = response.readEntity(AuthenticationResponseDto.class);
        }

        response.close();
        client.close();

        return responseData;
    }

    public
}
