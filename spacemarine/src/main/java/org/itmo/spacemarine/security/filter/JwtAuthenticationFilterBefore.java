package org.itmo.spacemarine.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.itmo.spacemarine.security.JacksonDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Optional;

@Slf4j
@Component
public class JwtAuthenticationFilterBefore extends OncePerRequestFilter {

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//        return "OPTIONS".equals(request.getMethod());
//    }

    private static final String BEARER_PREFIX = "Bearer";

    @Value("${jwt.public-key}")
    private String publicKeyString;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("ПРИШЕЛ ЗАПРОС С МЕТОДОМ  " + request.getMethod());
            if (request.getMethod().equals("OPTIONS")) return;
            Optional<String> optionalToken = extractAccessToken(request);
            if (optionalToken.isPresent() && StringUtils.hasLength(optionalToken.get())) {
                try {
                    PublicKey publicKey = getPublicKeyFromString(publicKeyString);
                    Jws<Claims> parseClaimsJws = Jwts
                            .parserBuilder()
                            .setSigningKey(publicKey)
                            .deserializeJsonWith(new JacksonDeserializer<>())
                            .build()
                            .parseClaimsJws(optionalToken.get());

                    String username = parseClaimsJws.getBody().getSubject();

                    log.info("Authorized successfully, username: " + username);

                } catch (JwtException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                    log.info("Token is incorrect!");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
                }
            } else {
                log.info("Token is empty!");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        } catch (Throwable t) {
            SecurityContextHolder.clearContext();
            log.info("Authorization error", t);
        }
        filterChain.doFilter(request, response);
    }

    private PublicKey getPublicKeyFromString(String publicKeyString)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }


    private Optional<String> extractAccessToken(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasLength(header)) {
            return Optional.of(header.substring(BEARER_PREFIX.length()).trim());
        }
        return Optional.empty();
    }
}
