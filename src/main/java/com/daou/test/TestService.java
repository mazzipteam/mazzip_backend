package com.daou.test;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepositoty userRepositoty;

    private final PasswordEncoder passwordEncoder;

    public User login(Login login) {
        var userId = login.getId();

        // 1. id를 통해 해당 엔티티 불러오기
        var user = userRepositoty.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        // 2. passwordEncoder match()를 통해 참 거짓 판별
        if(passwordEncoder.matches(login.getPassword(), user.getEncodedPwd())){
            // id와 pwd 정보는 반환 안함
            user.setUserId(null);
            user.setEncodedPwd(null);

            return user;
        }
        else throw new RuntimeException("Wrong Password");
    }

    public User signup(Signup signup) {
        var user = User.builder()
                .nickname(signup.getNickname())
                .userId(signup.getId())
                .encodedPwd(getEncodedPwd(signup.getPassword()))
                .build();
        userRepositoty.save(user);
        return user;
    }

    public String getEncodedPwd(String password) {
        return passwordEncoder.encode(password);
    }
}
