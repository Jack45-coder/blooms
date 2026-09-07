package in.codingage.blooms.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    // Placeholder for JWT utility methods (e.g., generateToken, ValidateToken, getUserNameFromToken)

    private final String SECRET = "scecretkeyformyblomsapplication109810480841831111";
    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Implement JWT token generation logic here
    public String generateToken(String username){
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", "USER");
        return Jwts.builder()
                .setSubject(username)
//                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try{

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            // TODO - check if token is in invalid token store
            // if token is present in invalid token store, return false
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
