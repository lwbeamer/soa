package itmo.corp.secondaryservice.service;

import itmo.corp.secondaryservice.client.StarshipRestClient;
import itmo.corp.secondaryservice.dto.AuthenticationRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StarshipService {

    @Inject
    private StarshipRestClient client;

    public String getToken() {
        AuthenticationRequestDto request = new AuthenticationRequestDto();
        request.setUsername("lwbeamer");
        request.setPassword("2281337");
        return client.sendPostRequest(request).getToken();
    }

    public void loadToStarship() {
        client.
    }


}
