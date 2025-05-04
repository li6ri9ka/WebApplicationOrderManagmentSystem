package com.example.WebApplicationOrderManagmentSystem;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils  {

    @Value("${jwt.secret}")
    private String secretBase64;
    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        // Фикс для кривых ключей
        String cleanedSecret = secretBase64.trim().replaceAll("=", "");
        byte[] keyBytes = Base64.getDecoder().decode(cleanedSecret);

        if (keyBytes.length != 64) {
            throw new IllegalStateException("Ключ должен быть 64 байта. Текущий: " + keyBytes.length);
        }

        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // ==================== ГЕНЕРАЦИЯ ====================
    public String generateToken(String username, String role, Long userId) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3_600_000)) // 1 час
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    // ==================== ПАРСИНГ (parseToken) ====================
    public Claims parseToken(String token) throws JwtException {
        if (token == null || token.isBlank()) {
            throw new JwtException("Токен пустой");
        }

        // Удаляем "Bearer " если есть
        token = token.replace("Bearer ", "").trim();

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ==================== ВАЛИДАЦИЯ ====================
    public boolean validateToken(String token) {
        try {
            parseToken(token); // Используем парсинг для валидации
            return true;
        } catch (JwtException e) {
            System.err.println("Ошибка валидации: " + e.getMessage());
            return false;
        }
    }

    public Long extractUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .get("userId", Long.class);
    }


    // ==================== ПОЛУЧЕНИЕ ДАННЫХ ====================
    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    public String getRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    public Long getUserId(String token) {
        return parseToken(token).get("userId", Long.class);
    }
}