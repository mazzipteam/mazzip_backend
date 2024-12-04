package com.project.api.avatar;

import com.project.api.avatar.dto.AvatarCreateDTO;
import com.project.api.avatar.dto.AvatarUpdateDTO;
import com.project.api.clothes.ClothesService;
import com.project.api.my_clothes.MyClothesService;
import com.project.api.my_clothes.dto.MyClothesCreateDTO;
import com.project.api.user.UserService;
import com.project.entity.Avatar;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.project.exception.error_code.AvatarErrorCode.AVATAR_NOT_FOUND;
import static com.project.exception.error_code.AvatarErrorCode.AVATAR_NOT_FOUND_BY_USER;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final UserService userService;
    private final MyClothesService myClothesService;
    private final ClothesService clothesService;

    public Avatar create(AvatarCreateDTO avatarCreateDTO) {
        var user = userService.getUser(avatarCreateDTO.getUserId());

        var avatar = Avatar.builder()
                .name(avatarCreateDTO.getName())
                .user(user)
                .level(1)
                .experience(0)
                .evolution(1)
                .build();

        avatarRepository.save(avatar);
        return avatar;
    }

    public Avatar update(AvatarUpdateDTO avatarUpdateDTO) {
        var avatar = getAvatar(avatarUpdateDTO.getAvatarId());

        if(avatarUpdateDTO.getName()!= null)
            avatar.setName(avatarUpdateDTO.getName());

        if(avatarUpdateDTO.getLevel()!= null)
            avatar.setLevel(Integer.parseInt(avatarUpdateDTO.getLevel()));

        avatarRepository.save(avatar);
        return avatar;
    }

    public Avatar delete(Long avatarId) {
        var avatar = getAvatar(avatarId);

        avatarRepository.deleteById(avatarId);
        return avatar;
    }

    public Avatar getAvatar(Long avatarId) {
        var avatar = avatarRepository.findByAvatarId(avatarId)
                .orElseThrow(() -> new ControlledException(AVATAR_NOT_FOUND));

        return avatar;
    }

    public Avatar expUp(Long avatarId, Integer exp) {
        var avatar = getAvatar(avatarId);

        avatar.setExperience(avatar.getExperience() + exp);
        avatarRepository.save(avatar);
        return avatar;
    }

    public Avatar eat(Long avatarId) {
        var avatar = getAvatar(avatarId);
        avatar.setEatCount(avatar.getEatCount() + 1);
        avatar.setEatDate(LocalDateTime.now());
        avatarRepository.save(avatar);
        return avatar;
    }

    public Avatar getAvatarByUserId(Long userId) {
        var user = userService.getUser(userId);
        var avatar = avatarRepository.findByUser(user)
                .orElseThrow(() -> new ControlledException(AVATAR_NOT_FOUND_BY_USER));

        return avatar;
    }

    public Avatar levelUp(Long avatarId) {
        var avatar = getAvatar(avatarId);
        avatar.setLevel(avatar.getLevel() + 1);
        avatarRepository.save(avatar);

        var clotheses = clothesService.getAllClothes();
        for (var clothes : clotheses) {
            if(clothes.getLimitLevel() == avatar.getLevel()){
                var myClothesCreateDto = MyClothesCreateDTO.builder()
                        .avatarId(avatarId)
                        .clothesId(clothes.getClothesId())
                        .build();

                myClothesService.create(myClothesCreateDto, avatar);
            }
        }

        return avatar;
    }
}
