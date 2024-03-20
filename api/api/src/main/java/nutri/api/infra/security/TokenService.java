package nutri.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import nutri.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generate(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Movies")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expiresAt())
                    .sign(algorithm);
        } catch(JWTCreationException exception) {
            throw new RuntimeException("erro: ", exception);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("API Movies")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido", exception);
        }
    }

    private Instant expiresAt() {
        return LocalDateTime.now().plusHours(2L).toInstant(ZoneOffset.of("-03:00"));
    }
}
