package connected.communication.factory.dto;

import connected.communication.dto.sign.RefreshTokenResponse;

public class RefreshTokenResponseFactory {
    public static RefreshTokenResponse createRefreshTokenResponse(String accessToken){
        return new RefreshTokenResponse(accessToken);
    }
}
