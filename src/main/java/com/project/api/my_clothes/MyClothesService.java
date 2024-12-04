package com.project.api.my_clothes;

import com.project.api.avatar.AvatarService;
import com.project.api.clothes.ClothesService;
import com.project.api.my_clothes.dto.MyClothesCreateDTO;
import com.project.api.my_clothes.dto.MyClothesUpdateDTO;
import com.project.entity.Avatar;
import com.project.entity.MyClothes;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.project.exception.error_code.MyClothesErrorCode.*;

@Service
@RequiredArgsConstructor
public class MyClothesService {
    private final MyClothesRepository myClothesRepository;
    private final ClothesService clothesService;

    public MyClothes create(MyClothesCreateDTO myClothesCreateDTO, Avatar avatar) {
        var clothes = clothesService.getClothes(myClothesCreateDTO.getClothesId());

        // 1. [예외처리] 이미 획득한 의상을 다시한번 생성하는 경우
        if(myClothesRepository.findByAvatarAndMyClothesId(avatar, clothes.getClothesId()).isPresent())
            throw new ControlledException(MY_CLOTHES_ALREADY_EXISTS);

        var myClothes = MyClothes.builder()
                .avatar(avatar)
                .clothes(clothes)
                .wear('N')
                .purchaseTime(LocalDateTime.now())
                .build();

        myClothesRepository.save(myClothes);
        return myClothes;
    }

    public MyClothes update(MyClothesUpdateDTO myClothesUpdateDTO) {
        var myClothes = getMyClothes(myClothesUpdateDTO.getMyClothesId());

        if(myClothesUpdateDTO.getWear() != null){
            if(!myClothesUpdateDTO.getWear().equals('Y')&&!myClothesUpdateDTO.getWear().equals('N'))
                throw new ControlledException(WEAR_OF_INCORRECT_FORMAT);
            myClothes.setWear(myClothesUpdateDTO.getWear());
        }

        myClothesRepository.save(myClothes);
        return myClothes;
    }

    public MyClothes delete(Long myClothesId) {
        var myClothes = getMyClothes(myClothesId);

        myClothesRepository.deleteById(myClothesId);
        return myClothes;
    }

    public MyClothes getMyClothes(Long myClothesId) {
        var myClothes = myClothesRepository.findByMyClothesId(myClothesId)
                .orElseThrow(() -> new ControlledException(MY_CLOTHES_NOT_FOUND));

        return myClothes;
    }

    public List<MyClothes> getAllMyClothes(Avatar avatar) {
        var allMyClothes = myClothesRepository.findByAvatar(avatar)
                .orElseThrow(() -> new ControlledException(MY_CLOTHES_NOT_FOUND_IN_AVATAR));

        return allMyClothes;
    }

    public MyClothes wear(MyClothes myClothes) {
        var allMyClothes = myClothesRepository.findByAvatar(myClothes.getAvatar())
                        .orElseThrow(()->new ControlledException(MY_CLOTHES_NOT_FOUND_IN_AVATAR));

        allMyClothes.forEach(param -> {
            param.setWear('N');
            myClothesRepository.save(param);
        });

        myClothes.setWear('Y');

        myClothesRepository.save(myClothes);
        return myClothes;
    }
}
