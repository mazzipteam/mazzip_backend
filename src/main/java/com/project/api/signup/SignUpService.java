package com.project.api.signup;

import com.project.api.user.UserService;
import com.project.entity.SignUp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final SignUpRepository signUpRepository;
    private final UserService userService;

    public SignUp create(SignUpDTO signUpDTO, MultipartFile multipartFileForeground, MultipartFile multipartFileInterior, MultipartFile multipartFileMenu) {
        var user = userService.getUser(signUpDTO.getUserId());

        try {
            var signUp = SignUp.builder()
                    .user(user)
                    .email(signUpDTO.getEmail())
                    .password(signUpDTO.getPassword())
                    .nickName(signUpDTO.getNickName())
                    .telNum(signUpDTO.getTelNum())
                    .address(signUpDTO.getAddress())
                    .detailAddress(signUpDTO.getDetailAddress())
                    .role(signUpDTO.getRole())
                    .name(signUpDTO.getName())
                    .restaurantAddress(signUpDTO.getRestaurantAddress())
                    .businessName(signUpDTO.getBusinessName())
                    .propritor(signUpDTO.getPropritor())
                    .category(signUpDTO.getCategory())
                    .restaurantTelNum(signUpDTO.getRestaurantTelNum())
                    .takeOut(signUpDTO.getTakeOut())
                    .menu(multipartFileMenu.getBytes())
                    .foreGround(multipartFileForeground.getBytes())
                    .interior(multipartFileInterior.getBytes())
                    .build();

            return signUp;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SignUp read(Long signUpId) {
        var signUp = signUpRepository.findBySignUpId(signUpId)
                .orElseThrow(()->new RuntimeException("SignUp not found"));

        return signUp;
    }

    public SignUp delete(Long signUpId) {
        var signUp = read(signUpId);
        signUpRepository.deleteById(signUpId);

        return signUp;
    }

    public List<SignUp> readAll(Long userId) {
        var user = userService.getUser(userId);
        var signUps = signUpRepository.findByUser(user)
                .orElseThrow(()->new RuntimeException("SignUp not found"));

        return signUps;
    }
}
