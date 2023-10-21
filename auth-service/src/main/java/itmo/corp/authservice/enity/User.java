package itmo.corp.authservice.enity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "users")
@RequiredArgsConstructor
public class User {

    @Id
    private String username;


    @JsonIgnore
    private String password;

}