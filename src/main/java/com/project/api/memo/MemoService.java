package com.project.api.memo;

import com.project.api.memo.dto.MemoCreateDTO;
import com.project.api.memo.dto.MemoUpdateDTO;
import com.project.api.restaurant.RestaurantService;
import com.project.api.user.UserService;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.api.memo.MemoErrorCode.MEMO_NOT_FOUND;

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
        var memo = memoRepository.findByMemoId(memoUpdateDTO.getMemoId())
                .orElseThrow(() -> new ControlledException(MEMO_NOT_FOUND));

        if(!memoUpdateDTO.getTitle().isEmpty())
            memo.setTitle(memoUpdateDTO.getTitle());

        if(!memoUpdateDTO.getDescription().isEmpty())
            memo.setDescription(memoUpdateDTO.getDescription());

        memoRepository.save(memo);
        return memo;
    }

    public Memo delete(Long memoId) {
        var memo = memoRepository.findByMemoId(memoId)
                .orElseThrow(() -> new ControlledException(MEMO_NOT_FOUND));

        memoRepository.deleteByMemoId(memoId);
        return memo;
    }

    public Memo getMemo(Long memoId) {
        var memo = memoRepository.findByMemoId(memoId)
                .orElseThrow(() -> new ControlledException(MEMO_NOT_FOUND));

        return memo;
    }
}
