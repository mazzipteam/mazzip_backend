package com.project.api.bookmark;

import com.project.api.restaurant.RestaurantService;
import com.project.api.user.UserService;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.project.api.bookmark.BookmarkErrorCode.BOOKMARK_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class BookmarkService {
    private final UserService userService;
    private final RestaurantService restaurantService;
    private final BookmarkRepository bookmarkRepository;

    public Bookmark create(BookmarkCreateDTO bookmarkCreateDTO) {
        var user = userService.getUser(bookmarkCreateDTO.getUserId());
        var restaurant = restaurantService.getRestaurant(bookmarkCreateDTO.getRestaurantId());

        var bookmark = Bookmark.builder()
                .user(user)
                .restaurant(restaurant)
                .build();

        bookmarkRepository.save(bookmark);
        return bookmark;
    }

    public Bookmark delete(Long bookmarkId) {
        var bookmark = bookmarkRepository.findByBookmarkId(bookmarkId)
                .orElseThrow(()->new ControlledException(BOOKMARK_NOT_FOUND));

        bookmarkRepository.deleteByBookmarkId(bookmarkId);
        return bookmark;
    }

    public Bookmark getBookmark(Long bookmarkId) {
        var bookmark = bookmarkRepository.findByBookmarkId(bookmarkId)
                .orElseThrow(()->new ControlledException(BOOKMARK_NOT_FOUND));

        return bookmark;
    }
}
