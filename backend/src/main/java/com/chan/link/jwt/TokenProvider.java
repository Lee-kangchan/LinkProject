package com.chan.link.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


/*
* 토큰의 생성 토큰의 유효성 검증을 담당
* */
@Slf4j
@Component
//InitializingBean -> Bean 생성 (DisposableBean -> Bean 파괴)
public class TokenProvider implements InitializingBean {

    private static final String AUTHORITY_KEY = "auth";
    private Key key;
    private final String secret;
    private final long tokenValidityInMilliseconds;


    //application.yml에 저장했던 jwt의 secret 과 유효시간 불러오기
    public TokenProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.token-validity-in-seconds}") long tokenValidityInMilliseconds){
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInMilliseconds;
    }

    @Override
    //Bean 생성 후 주입 받은 후 key에 변수 할당
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret); // secret 값을 디코딩
        this.key = Keys.hmacShaKeyFor(keyBytes); //key 변수 저장
    }

    //Authentication 객체의 권한정보를 이용해서 토큰 생성하는 createToken 메소드
    public String createToken(Authentication authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds); // 현재시간부터 유효시간까지

        return Jwts.builder().setSubject(authentication.getName())
                .claim(AUTHORITY_KEY, authorities)
                .signWith(key, SignatureAlgorithm.HS512)
                .setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token){
        // token을 받아서 claim으로 만들고 이를 이용해 유저 객체를 만들어서 Authentication 객체를 리턴
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITY_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }


    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e){
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e){
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e){
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
