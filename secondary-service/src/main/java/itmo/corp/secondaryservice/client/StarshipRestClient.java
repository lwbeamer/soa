package itmo.corp.secondaryservice.client;

import itmo.corp.secondaryservice.config.AppConfig;
import itmo.corp.secondaryservice.dto.Error;
import itmo.corp.secondaryservice.dto.Page;
import itmo.corp.secondaryservice.dto.SpaceMarine;
import itmo.corp.secondaryservice.dto.SpaceMarineResponseDto;
import itmo.corp.secondaryservice.dto.SpaceMarineUpdateDto;
import itmo.corp.secondaryservice.exception.ClientException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

@ApplicationScoped
public class StarshipRestClient {
    private static final String SPACE_MARINE_BASE_URL = AppConfig.getProperty("spacemarine.api");

    public static SSLContext createSSLContext(String truststorePath, String truststorePassword) throws Exception {
        KeyStore truststore = KeyStore.getInstance(AppConfig.getProperty("truststore.type"));

        try (InputStream truststoreInput = new FileInputStream(truststorePath)) {
            truststore.load(truststoreInput, truststorePassword.toCharArray());
        }

        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(truststore);

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, tmf.getTrustManagers(), null);

        return sslContext;
    }

    public static Client createSSLClient() throws Exception {
        SSLContext sslContext = createSSLContext(
                AppConfig.getProperty("truststore.path"),
                AppConfig.getProperty("truststore.key")
        );

        return ClientBuilder.newBuilder()
                .sslContext(sslContext)
                .build();
    }

    @SneakyThrows
    public SpaceMarineResponseDto getSpaceMarineById(long id, String token) {
        Client client = createSSLClient();

        Response response = null;
        SpaceMarineResponseDto responseData = null;
        try {
            response = client
                    .target(SPACE_MARINE_BASE_URL + "/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .get();

            if (response.getStatus() == 200) {
                responseData = response.readEntity(SpaceMarineResponseDto.class);
                System.out.println(responseData);
            }

            response.close();
            client.close();
        } catch (Exception e) {
            if (response != null) response.close();
            client.close();
            throw new ClientException(Error.builder()
                    .code(418)
                    .message("Ошибка отправки запроса на главный сервис!")
                    .build());
        }

        return responseData;
    }

    @SneakyThrows
    public Error updateSpaceMarineRequest(long id, SpaceMarineUpdateDto requestData, String token) {
        Client client = createSSLClient();
        Error error = null;

        Response response = null;
        try {
            response = client
                    .target(SPACE_MARINE_BASE_URL + "/" + id)
                    .request(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .put(Entity.entity(requestData, MediaType.APPLICATION_JSON));

            if (response.getStatus() == 200) {
                System.out.println("PUT request was successful");
            } else {
                error = response.readEntity(Error.class);
            }

            response.close();
            client.close();
        } catch (Exception e) {
            error = Error.builder()
                    .code(418)
                    .message("Ошибка отправки запроса на главный сервис!")
                    .build();
            if (response != null) response.close();
            client.close();
        }
        return error;
    }

    @SneakyThrows
    public Page<SpaceMarine> getAllSpaceMarinesInStarship(long starshipId, String token) {
        Client client = createSSLClient();

        Response response = null;
        Page<SpaceMarine> responseData = null;
        try {

            response = client
                    .target(SPACE_MARINE_BASE_URL)
                    .queryParam("starshipId", starshipId)
                    .queryParam("pageSize", Integer.MAX_VALUE)
                    .request(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .get();


            if (response.getStatus() == 200) {
                responseData = response.readEntity(new GenericType<>() {
                });
            }

            response.close();
            client.close();

        } catch (Exception e) {
            if (response != null) response.close();
            client.close();
            throw new ClientException(Error.builder()
                    .code(418)
                    .message("Ошибка отправки запроса на главный сервис!")
                    .build());
        }
        return responseData;
    }
}
