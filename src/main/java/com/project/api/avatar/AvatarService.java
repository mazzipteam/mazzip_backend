package com.project.api.avatar;

import com.project.api.avatar.dto.AvatarCreateDTO;
import com.project.api.avatar.dto.AvatarUpdateDTO;
import com.project.api.user.UserService;
import com.project.entity.Avatar;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.exception.errorcode.AvatarErrorCode.AVATAR_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final UserService userService;

    public Avatar create(AvatarCreateDTO avatarCreateDTO) {
        var user = userService.getUser(avatarCreateDTO.getUserId());

        var avatar = Avatar.builder()
                .name(avatarCreateDTO.getName())
                .user(user)
                .build();

        avatarRepository.save(avatar);
        return avatar;
    }

    public Avatar update(AvatarUpdateDTO avatarUpdateDTO) {
        var avatar = avatarRepository.findByAvatarId(avatarUpdateDTO.getAvatarId())
                .orElseThrow(() -> new ControlledException(AVATAR_NOT_FOUND));

        if(avatarUpdateDTO.getName() != null)
            avatar.setName(avatarUpdateDTO.getName());

        avatarRepository.save(avatar);
        return avatar;
    }

    public Avatar delete(Long avatarId) {
        var avatar = avatarRepository.findByAvatarId(avatarId)
                .orElseThrow(() -> new ControlledException(AVATAR_NOT_FOUND));

        avatarRepository.deleteById(avatarId);
        return avatar;
    }

    public Avatar getAvatar(Long avatarId) {
        var avatar = avatarRepository.findByAvatarId(avatarId)
                .orElseThrow(() -> new ControlledException(AVATAR_NOT_FOUND));

        return avatar;
    }
}
