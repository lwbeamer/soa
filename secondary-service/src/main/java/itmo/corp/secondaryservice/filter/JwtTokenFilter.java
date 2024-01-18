//package itmo.corp.secondaryservice.filter;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import itmo.corp.secondaryservice.config.AppConfig;
//import itmo.corp.secondaryservice.config.JacksonDeserializer;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.security.KeyFactory;
//import java.security.NoSuchAlgorithmException;
//import java.security.PublicKey;
//import java.security.spec.InvalidKeySpecException;
//import java.security.spec.X509EncodedKeySpec;
//import java.util.Base64;
//
//@WebFilter(urlPatterns = "/api/v1/starships/*")
//public class JwtTokenFilter implements Filter {
//
//    private final String publicKeyString = AppConfig.getProperty("jwt.public-key");
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
//        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
//        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//        httpResponse.setHeader("Access-Control-Allow-Headers", "*");
//        httpResponse.setHeader("Access-Control-Allow-Methods", "*");
//        if (httpRequest.getMethod().equals("OPTIONS")) return;
//
//        String token = httpRequest.getHeader("Authorization");
//
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7); // Убираем префикс "Bearer "
//            try {
//                PublicKey publicKey = getPublicKeyFromString(publicKeyString,"R SA");
//                Jws<Claims> parseClaimsJws = Jwts
//                        .parserBuilder()
//                        .setSigningKey(publicKey)
//                        .deserializeJsonWith(new JacksonDeserializer<>())
//                        .build()
//                        .parseClaimsJws(token);
//
//                String username = parseClaimsJws.getBody().getSubject();
//
//                System.out.println("Authorized successfully, username: " + username);
//
//            } catch (JwtException | NoSuchAlgorithmException | InvalidKeySpecException e) {
//                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                return;
//            }
//        } else {
//            System.out.println("Token is empty!");
//            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//
//    private PublicKey getPublicKфeyFromString(String publicKeyString, String algorithm)
//            throws NoSuchAlgorithmException, InvalidKeySpecException {
//        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
//        return keyFactory.generatePublic(keySpec);
//    }
//}
