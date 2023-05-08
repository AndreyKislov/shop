package ua.kislov.shop_back.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ua.kislov.shop_back.details.ClientDetails;
import ua.kislov.shop_back.model.SecurityShopClient;


@Component
public class JWTUtil {

    @Value("${jwt.secret-string}")
    private String secret;

    public Authentication validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Kislov")
                .build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return getAuth(decodedJWT);
    }
    private Authentication getAuth(DecodedJWT decodedJWT){
        Claim role = decodedJWT.getClaim("role");
        Claim user = decodedJWT.getClaim("user");
        String password = "somePassword";
        if(role.isNull() && user.isNull()) {
            SecurityShopClient client = new SecurityShopClient(0, "user", "password", "ROLE_USER");
            ClientDetails details = new ClientDetails(client);
            return new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
        }else {
            SecurityShopClient client = new SecurityShopClient(0, user.asString(), password, "ROLE_ADMIN");
            ClientDetails details = new ClientDetails(client);
            return new UsernamePasswordAuthenticationToken(details, details.getPassword(), details.getAuthorities());
        }
    }
}
