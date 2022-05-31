package connected.communication.handler;

import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtHandler {
    private String type = "bluish";

    public String createToken(String encodedKey, String subject, long maxAgeSeconds){
        Date now = new Date();
        return type + Jwts.builder() //1.JWT 빌드 시작
                .setSubject(subject) //2.토큰에 저장될 데이터를 지정
                .setIssuedAt(now) //3.토근 발급일을 지정한다.
                .setExpiration(new Date(now.getTime() + maxAgeSeconds * 1000L)) //4.토근 만료일자를 지정해준다.
                .signWith(SignatureAlgorithm.HS256,encodedKey)//5.파라미터로 받은 key를 SHA-256 알고리즘을 사용하여 서명함
                .compact();//6.주어진 정보로 토큰을 생성한다.
    }

    /**
     * 토큰에서 subject를 추출해낸다.
     * 토큰을 파싱하고, 바디에서 subject를 꺼내올 수 있다.
     * 현재 서비스에서는, 토큰의 subject로 Member의 id가 저장되기 때문에, 이를 이용하여 사용자를 인증할 수 있을 것이라 예상됩니다.
     */
    public String extractSubject(String encodedKey, String token) {
        return parse(encodedKey, token).getBody().getSubject();
    }

    /**
     * 토큰의 유효성을 검증한다.
     */
    public boolean validate(String encodedKey, String token){
        try{
            parse(encodedKey, token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }

    /**
     * 토큰을 파싱한다.
     * 토큰을 파싱하는 과정은 아래와 같다.
     * parse를 이용하여 사용된 key를 지정해주고, 파싱을 수행한다.
     * 이 때, 토큰 문자열에는 토큰의 타입도 포함되어있으므로, 이를 untype 메소드를 이용하여 제거한다.
     */

    private Jws<Claims> parse(String key, String token){
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(untype(token));
    }

    private String untype(String token){
        return token.substring(type.length());
    }
}
