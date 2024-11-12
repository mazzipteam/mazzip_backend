package com.project.api.token;

import com.project.api.user.UserService;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.api.token.TokenErrorCode.TOKEN_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final UserService userService;

    public Token create(TokenCreateDTO tokenCreateDTO) {
        var user = userService.getUser(tokenCreateDTO.getUserId());

        var token = Token.builder()
                .fcmToken(tokenCreateDTO.getFcmToken())
                .user(user)
                .build();

        tokenRepository.save(token);
        return token;
    }

    public Token update(TokenUpdateDTO tokenUpdateDTO) {
        var token = tokenRepository.findByTokenId(tokenUpdateDTO.getTokenId())
                .orElseThrow(()->new ControlledException(TOKEN_NOT_FOUND));

        if(!tokenUpdateDTO.getFcmToken().isEmpty())
            token.setFcmToken(tokenUpdateDTO.getFcmToken());

        tokenRepository.save(token);
        return token;
    }

    public Token delete(Long tokenId) {
        var token = tokenRepository.findByTokenId(tokenId)
                .orElseThrow(()->new ControlledException(TOKEN_NOT_FOUND));

        tokenRepository.deleteByTokenId(tokenId);
        return token;
    }

    public Token getToken(Long tokenId) {
        var token = tokenRepository.findByTokenId(tokenId)
                .orElseThrow(()->new ControlledException(TOKEN_NOT_FOUND));

        return token;
    }
}
