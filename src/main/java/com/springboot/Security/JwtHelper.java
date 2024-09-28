package com.springboot.Security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtHelper {

    private static final long JWT_TOKEN_VALIDITY = 1000 * 60 * 60; // 1 hour expiration time
    private final String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    // Retrieve username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from JWT token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // For retrieving any information from the token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // Decode and get the key from the secret
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);  // Decode the secret key
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate token for the user, including roles
    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, authentication);
    }

    // Generate JWT Token by including multiple roles in claims
    private String doGenerateToken(Map<String, Object> claims, Authentication authentication) {
        // Extract multiple roles from the Authentication object
        Set<String> roles = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toSet());  // Collect roles as a Set

        claims.put("roles", roles);  // Store roles in JWT claims

        // Build and sign the JWT token
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(authentication.getName())  // Set the username as the subject
                .setIssuedAt(new Date(System.currentTimeMillis()))  // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))  // Set expiration time
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // Sign the token with secret key
                .compact();
    }

    // Validate the token with username and check for expiration
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // Extract roles from JWT token
    public Set<String> getRolesFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Set<String>) claims.get("roles");  // Retrieve roles from the claims
    }
}
