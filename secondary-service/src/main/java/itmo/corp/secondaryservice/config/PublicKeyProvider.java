package itmo.corp.secondaryservice.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;

@ApplicationScoped
public class PublicKeyProvider {
    @Produces
    public String getSecretKey() {
        return "azzazzazazza";
    }
}
