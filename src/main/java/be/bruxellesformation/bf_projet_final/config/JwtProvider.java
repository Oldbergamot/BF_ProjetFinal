package be.bruxellesformation.bf_projet_final.config;

import be.bruxellesformation.bf_projet_final.model.entity.User;
import be.bruxellesformation.bf_projet_final.util.JwtProperties;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final JwtProperties jwtProperties;

    @Autowired
    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }


    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expire = now.plus(jwtProperties.getExpiration(), ChronoUnit.SECONDS);

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(now.toInstant(ZoneOffset.UTC)))
                .setExpiration(Date.from(expire.toInstant(ZoneOffset.UTC)))
                .claim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtProperties.getSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token);
            return true;
        }
        catch (SignatureException | MalformedJwtException | UnsupportedJwtException | ExpiredJwtException |IllegalArgumentException ignored) { }

        return false;
    }
}

