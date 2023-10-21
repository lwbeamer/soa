package itmo.corp.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.private}")
    String privateKeyString;

    public String generateJwtToken(String username) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PrivateKey privateKey = getPrivateKeyFromString(privateKeyString, "RSA");

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public PrivateKey getPrivateKeyFromString(String privateKeyString, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePrivate(keySpec);
    }
}
