package me.hwangje.springbootdeveloper.config.jwt;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import me.hwangje.springbootdeveloper.domain.User;
import org.springframework.security.web.header.Header;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()),user);
    }

    //JWT 토큰 생성 메서드
    private String makeToken(Date expiry, User user){
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더 typ : JWT
                //내용 iss : ajufresh@gamil.com(propertise 파일에서 설정한 값)
                .setIssuer(jwtProperties.getIssuer())
                .setIssuerAt(now) //내용 iat : 현재 시간
                .setExpiration(expiry) //내용 exp : expiry 멤버 변수값
                .setSubject(user.getEmail()) //내용 sub : 유저의 이메일
                .claim("id", user.getId()) // 클레임 id : 유저 ID
                // 서명: 비밀 값과 함께 해시 값을 HS256 방식으로 암호화
                .signWith(SingnatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    //토큰 유효성 검증 메서드
    public boolean validToken(String token){
        try {
            Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey()) //비밀값으로 복호화
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) { //복호화 과정에서 에러가 나면 유효하지 않은토큰
            return false;
        }
    }
}
