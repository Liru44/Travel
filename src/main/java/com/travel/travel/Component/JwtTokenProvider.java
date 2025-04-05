    package com.travel.travel.Component;

    import io.jsonwebtoken.JwtException;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.stereotype.Component;

    import javax.crypto.SecretKey;
    import java.util.Date;

    @Component
    public class JwtTokenProvider {
        private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

        public String generateToken(String username) {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                    .compact();
        }

        public String getUsernameFromToken(String token) {
            return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build()
                    .parseClaimsJws(token).getBody().getSubject();
        }

        public boolean validateToken(String token) {
            try {
                Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
                return true;
            } catch (JwtException | IllegalArgumentException e) {
                return false;
            }
        }
    }