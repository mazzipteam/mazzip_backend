package com.project.api.memo;

import com.project.api.memo.dto.MemoCreateDTO;
import com.project.api.memo.dto.MemoUpdateDTO;
import com.project.api.restaurant.RestaurantService;
import com.project.api.user.UserService;
import com.project.entity.Memo;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.project.exception.error_code.MemoErrorCode.MEMO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final MemoRepository memoRepository;

    public Memo create(MemoCreateDTO memoCreateDTO) {
        var user = userService.getUser(memoCreateDTO.getUserId());
        var restaurant = restaurantService.getRestaurant(memoCreateDTO.getRestaurantId());

        var memo = Memo.builder()
                .title(memoCreateDTO.getTitle())
                .description(memoCreateDTO.getDescription())
                .user(user)
                .restaurant(restaurant)
                .build();

        memoRepository.save(memo);
        return memo;
    }

    public Memo update(MemoUpdateDTO memoUpdateDTO) {
        var memo = getMemo(memoUpdateDTO.getMemoId());

        if(memoUpdateDTO.getTitle()!= null)
            memo.setTitle(memoUpdateDTO.getTitle());

        if(memoUpdateDTO.getDescription()!= null)
            memo.setDescription(memoUpdateDTO.getDescription());

        memo.setUpdatedAt(LocalDateTime.now());

        memoRepository.save(memo);
        return memo;
    }

    public Memo delete(Long memoId) {
        var memo = getMemo(memoId);

        memoRepository.deleteByMemoId(memoId);
        return memo;
    }

    public Memo getMemo(Long memoId) {
        var memo = memoRepository.findByMemoId(memoId)
                .orElseThrow(() -> new ControlledException(MEMO_NOT_FOUND));

        return memo;
    }
}
