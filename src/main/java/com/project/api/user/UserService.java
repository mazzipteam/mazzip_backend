package com.project.api.user;

import com.project.api.user.dto.UserCreateDTO;
import com.project.api.user.dto.UserUpdateDTO;
import com.project.entity.user.Role;
import com.project.entity.user.User;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import static com.project.exception.error_code.UserErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User create(UserCreateDTO userCreateDTO) {
        // 1. [예외처리] 이메일 서식 오류
        if(!isValidEmail(userCreateDTO.getEmail()))
            throw new ControlledException(EMAIL_OF_INCORRECT_FORMAT);

        // 2. [예외처리] 전화번호 서식 오류
        if(!isValidTelNum(userCreateDTO.getTelNum()))
            throw new ControlledException(TELNUM_OF_INCORRECT_FORMAT);

        // 3. [예외처리] 이미 존재하는 email
        if(userRepository.findByEmail(userCreateDTO.getEmail()).isPresent())
            throw new ControlledException(EMAIL_ALREADY_EXISTS);

        // 4. [예외처리] 이미 존재하는 nickName
        if(userRepository.findByNickName(userCreateDTO.getNickName()).isPresent())
            throw new ControlledException(NICKNAME_ALREADY_EXISTS);

        // 5. [예외처리] 잘못된 형식의 role
        Role role;
        try {
            role = Role.valueOf(userCreateDTO.getRole());
        } catch (IllegalArgumentException e) {
            throw new ControlledException(ROLE_OF_INCORRECT_FORMAT);
        }

        var user = User.builder()
                .email(userCreateDTO.getEmail())
                .password(userCreateDTO.getPassword())
                .nickName(userCreateDTO.getNickName())
                .telNum(userCreateDTO.getTelNum())
                .address(userCreateDTO.getAddress())
                .detailAddress(userCreateDTO.getDetailAddress())
                .createAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(role)
                .build();

        userRepository.save(user);
        return user;
    }


    public User update(UserUpdateDTO userUpdateDTO) {
        // 1. [예외처리] 전화번호 서식 오류
        if(!isValidTelNum(userUpdateDTO.getTelNum()))
            throw new ControlledException(TELNUM_OF_INCORRECT_FORMAT);

        // 2. [예외처리] 이미 존재하는 nickName
        if(userRepository.findByNickName(userUpdateDTO.getNickName()).isPresent())
            throw new ControlledException(NICKNAME_ALREADY_EXISTS);

        var user = getUser(userUpdateDTO.getUserId());
        if(userUpdateDTO.getPassword() != null)
            user.setPassword(userUpdateDTO.getPassword());

        if(userUpdateDTO.getNickName()!= null)
            user.setNickName(userUpdateDTO.getNickName());

        if(userUpdateDTO.getTelNum()!= null)
            user.setTelNum(userUpdateDTO.getTelNum());

        if(userUpdateDTO.getAddress()!= null)
            user.setAddress(userUpdateDTO.getAddress());

        if(userUpdateDTO.getDetailAddress()!= null)
            user.setDetailAddress(userUpdateDTO.getDetailAddress());

        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
        return user;
    }

    public User delete(Long userId) {
        var user = getUser(userId);

        userRepository.deleteById(userId);
        return user;
    }

    public User getUser(Long userId) {
        var user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new ControlledException(USER_NOT_FOUND));

        return user;
    }

    public static boolean isValidTelNum(String telNum) {
        return telNum.length() == 11 && telNum.matches("\\d+");
    }

    private static boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

        if (email == null) {
            return false;
        }
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public User login(LoginDTO loginDTO) {
        var user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ControlledException(USER_NOT_FOUND_BY_EMAIL));

        if(!user.getPassword().equals(loginDTO.getPassword()))
            throw new ControlledException(USER_NOT_FOUND_BY_PASSWORD);

        return user;
    }

    public User findEmail(String nickName, Long telNum) {
        var user = userRepository.findByNickName(nickName)
                .orElseThrow(() -> new ControlledException(USER_NOT_FOUND_BY_NICK_NAME));

        if(user.getTelNum().equals(telNum)) throw new ControlledException(TELNUM_OF_INCORRECT_FORMAT);

        return user;
    }

    public User findPassword(String nickName, Long telNum, String email) {
        var user = userRepository.findByNickName(nickName)
                .orElseThrow(() -> new ControlledException(USER_NOT_FOUND_BY_NICK_NAME));

        if(user.getTelNum().equals(telNum)) throw new ControlledException(TELNUM_OF_INCORRECT_FORMAT);
        if(user.getEmail().equals(email)) throw new ControlledException(EMAIL_ALREADY_EXISTS);

        return user;
    }
}
