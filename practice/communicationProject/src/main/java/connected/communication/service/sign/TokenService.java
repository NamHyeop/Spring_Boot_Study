//package connected.communication.service.sign;
//
//import connected.communication.handler.JwtHandler;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
///**
// * Refactorying으로 쓸모없어진 파일
// */
//@Service
//@RequiredArgsConstructor
//public class TokenService {
//    private final JwtHandler jwtHandler;
//
//    /***
//     * Value 어노테이션을 이용하여 설정 파일에 작성된 내용을 가져온다.
//     * 그리고 JwtHandler를 사용하여 accessToken과 refreshToken을 가져온다.
//     */
//    @Value("${jwt.max-age.access}")
//    private long accessTokenMaxAgeSeconds;
//
//    @Value("${jwt.max-age.refresh}")
//    private long refreshTokenMaxAgeSeconds;
//
//    @Value("${jwt.key.access}")
//    private String accessKey;
//
//    @Value("${jwt.key.refresh}")
//    private String refreshKey;
//
//    public String createAccessToken(String subject){
//        return jwtHandler.createToken(accessKey, subject, accessTokenMaxAgeSeconds);
//    }
//
//    public String createRefreshToken(String subject){
//        return jwtHandler.createToken(refreshKey, subject, refreshTokenMaxAgeSeconds);
//    }
//
//    public boolean validateAccessToken(String token){
//        return jwtHandler.validate(accessKey,token);
//    }
//
//    public boolean validateRefreshToken(String token){
//        return jwtHandler.validate(refreshKey, token);
//    }
//
//    public String extractAccessTokenSubject(String token){
//        return jwtHandler.extractSubject(accessKey, token);
//    }
//
//    public String extractRefreshTokenSubject(String token){
//        return jwtHandler.extractSubject(refreshKey,token);
//    }
//}
//
//
