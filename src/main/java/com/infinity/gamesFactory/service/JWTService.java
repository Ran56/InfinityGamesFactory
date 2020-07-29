package com.infinity.gamesFactory.service;


import com.infinity.gamesFactory.model.Role;
import com.infinity.gamesFactory.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class JWTService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final String SECRET_KEY = System.getProperty("secret.key");
    private final String ISSUER = "com.infinity";
    private final long EXPIRATION_TIME = 86400 * 1000;


    public String generateToken(User user)
    {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims();
        claims.setId(String.valueOf(user.getId()));
        claims.setSubject(user.getName());
        claims.setIssuedAt(new Date(System.currentTimeMillis()));
        claims.setIssuer(ISSUER);
        claims.setExpiration(new Date((System.currentTimeMillis()+EXPIRATION_TIME)));

        Set<Role> roles = user.getRoles();
        String allowedReadResources = "";
        String allowedCreateResources = "";
        String allowedUpdateResources = "";
        String allowedDeleteResources = "";

        for(Role role:roles)
        {
            if(role.isAllowedRead()) allowedReadResources = String.join(role.getAllowedResource(),allowedReadResources,",");
            if(role.isAllowedDelete()) allowedDeleteResources = String.join(role.getAllowedResource(),allowedDeleteResources,",");
            if(role.isAllowedCreate()) allowedCreateResources = String.join(role.getAllowedResource(),allowedCreateResources,",");
            if(role.isAllowedUpdate()) allowedUpdateResources = String.join(role.getAllowedResource(),allowedUpdateResources,",");
        }

        claims.put("allowedReadResources",allowedReadResources.replaceAll(",$",""));
        claims.put("allowedDeleteResources",allowedDeleteResources.replaceAll(",$",""));
        claims.put("allowedCreateResources",allowedCreateResources.replaceAll(",$",""));
        claims.put("allowedUpdateResources",allowedUpdateResources.replaceAll(",$",""));


        JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm,signingKey);
        return builder.compact();
    }


    public  Claims decryptJwtToken(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getBody();
        logger.debug("Claims: "+ claims.toString());
        return claims;
    }


}
