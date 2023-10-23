package itmo.corp.secondaryservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import itmo.corp.secondaryservice.config.JacksonDeserializer;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

@WebFilter(urlPatterns = "/api/starships/*")
public class JwtTokenFilter implements Filter {

    private final String publicKeyString = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3SjfZDG9O2Wa7UwVrG2lFHFPr2PVAby50NhCmfstFvR8+MgimBSndRMr7eEYjkbRbT7fLVqJxH3ORunfaMJUZYeAwjKDjDMaO8Ye1SA0LCOejK0PJaHOtG7vlwv9vN1TPnEhiWZulrcfnPRI5eshf25ZEfTNUvwdlqAJ3LrKjnl66OsiZqkcujKkp+VSRO/TbEigOG/z6R+CcXr5ue0FUbawyrTy5NcYzxtKTOEpF6/572EXRbAOkOUKiJFJtmaBB4kiuw6Z2U5Cw9Ii+ntdhW45SPrBK+Eg78ag9YI+fRhJDgJnx2YyVbOSw5SXQj81uNGW5Sc0tV/+DDMbMh5T7wIDAQAB";


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String token = httpRequest.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Убираем префикс "Bearer "
            try {
                PublicKey publicKey = getPublicKeyFromString(publicKeyString,"RSA");
                Jws<Claims> parseClaimsJws = Jwts
                        .parserBuilder()
                        .setSigningKey(publicKey)
                        .deserializeJsonWith(new JacksonDeserializer<>())
                        .build()
                        .parseClaimsJws(token);

                String username = parseClaimsJws.getBody().getSubject();

                System.out.println("USERNAME = " + username);

            } catch (JwtException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else {
            System.out.println("Token is empty!");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private PublicKey getPublicKeyFromString(String publicKeyString, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        return keyFactory.generatePublic(keySpec);
    }
}
