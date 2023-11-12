package store.bakery.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TokenProvider {
    @Value("${app.token.signKey}")
    private String signingKey;
    public String generateAccessToken(Authentication authentication) {
        String roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        LocalDateTime expiredTime = LocalDateTime.now().plusHours(1); // Tính thời gian hết hạn của token, là thòi gian hiện tại cộng thêm 1 giờ
        return Jwts.builder()
                .setSubject(authentication.getName()) // email
                .claim("roles", roles)
                .setExpiration(Date.from(expiredTime.atZone(ZoneId.systemDefault()).toInstant())) // convest localdatetime sang java
                .signWith(SignatureAlgorithm.HS512, signingKey) // Ký JWT bằng thuật toán HS512 và khóa bí mật
                .compact();// Kết thúc xây dựng JWT và chuyển thành chuỗi
    }

    public Authentication getAuthentication(String token) {

        if (!StringUtils.hasText(token)) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token)
                    .getBody();
            List<GrantedAuthority> roles = Arrays.stream(claims.get("roles").toString().split(","))
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            User user = new User(claims.getSubject(), "", roles);
            return new UsernamePasswordAuthenticationToken(user, null, roles);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
