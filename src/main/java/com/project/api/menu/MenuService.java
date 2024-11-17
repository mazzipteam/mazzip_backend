package com.project.api.menu;

import com.project.api.food_category.FoodCategoryService;
import com.project.api.menu.dto.MenuCreateDTO;
import com.project.api.menu.dto.MenuUpdateDTO;
import com.project.api.restaurant.RestaurantService;
import com.project.entity.Menu;
import com.project.exception.ControlledException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.exception.error_code.MenuErrorCode.*;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantService restaurantService;
    private final FoodCategoryService foodCategoryService;

    public Menu create(MenuCreateDTO menuCreateDTO) {
        var restaurant = restaurantService.getRestaurant(menuCreateDTO.getRestaurantId());
        var foodCategory = foodCategoryService.getFoodCategory(menuCreateDTO.getFoodCategoryId());

        // 1. [예외처리] 이미 연결된 레스토랑이 있는 경우
        if(restaurantService.getRestaurant(menuCreateDTO.getRestaurantId()) != null)
            throw new ControlledException(RESTAURANT_ALREADY_EXISTS);

        var menu = Menu.builder()
                .name(menuCreateDTO.getName())
                .price(menuCreateDTO.getPrice())
                .description(menuCreateDTO.getDescription())
                .cheap(menuCreateDTO.getCheap())
                .image(menuCreateDTO.getImage())
                .main(menuCreateDTO.getMain())
                .isReserve(menuCreateDTO.getIsReserve())
                .restaurant(restaurant)
                .foodCategory(foodCategory)
                .build();

        menuRepository.save(menu);
        return menu;
    }

    public Menu update(MenuUpdateDTO menuUpdateDTO) {
        var menu = getMenu(menuUpdateDTO.getMenuId());

        if(menuUpdateDTO.getFoodCategoryId() != null){
            // 1. [에러처리]
            try {
                var foodCategory = foodCategoryService.getFoodCategory(Long.parseLong(menuUpdateDTO.getFoodCategoryId()));
                menu.setFoodCategory(foodCategory);
            } catch (NumberFormatException e) {
                throw new ControlledException(FOOD_CATEGORY_INCORRECT_FORMAT);
            }
        }

        if(menuUpdateDTO.getName() != null)
            menu.setName(menuUpdateDTO.getName());

        if(menuUpdateDTO.getPrice() != null){
            // 2. [에러처리]
            try {
                menu.setPrice(Integer.parseInt(menuUpdateDTO.getPrice()));
            } catch (NumberFormatException e) {
                throw new ControlledException(PRICE_INCORRECT_FORMAT);
            }
        }

        if(menuUpdateDTO.getDescription() != null)
            menu.setDescription(menuUpdateDTO.getDescription());

        if(menuUpdateDTO.getCheap() != null) {
            // 3. [에러처리]
            if(!menuUpdateDTO.getCheap().equals("Y") && !menuUpdateDTO.getCheap().equals("N"))
                throw new ControlledException(CHEAP_INCORRECT_FORMAT);
            menu.setCheap(menuUpdateDTO.getCheap().charAt(0));
        }

        if(menuUpdateDTO.getImage() != null) {
            // 4. [에러처리]
            try {
                menu.setImage(menuUpdateDTO.getImage().getBytes());
            } catch (IllegalArgumentException e) {
                throw new ControlledException(IMAGE_OF_INCORRECT_FORMAT);
            }
        }

        if(menuUpdateDTO.getMain() != null) {
            // 5. [에러처리]
            if(!menuUpdateDTO.getMain().equals("Y") && !menuUpdateDTO.getMain().equals("N"))
                throw new ControlledException(MAIN_INCORRECT_FORMAT);
            menu.setMain(menuUpdateDTO.getMain().charAt(0));
        }

        if(menuUpdateDTO.getIsReserve() != null) {
            // 6. [에러처리]
            if(!menuUpdateDTO.getIsReserve().equals("Y") && !menuUpdateDTO.getIsReserve().equals("N"))
                throw new ControlledException(IS_RESERVE_INCORRECT_FORMAT);
            menu.setIsReserve(menuUpdateDTO.getIsReserve().charAt(0));
        }

        menuRepository.save(menu);
        return menu;
    }

    public Menu delete(Long menuId) {
        var menu = getMenu(menuId);

        menuRepository.deleteByMenuId(menuId);
        return menu;
    }

    public Menu getMenu(Long menuId) {
        var menu = menuRepository.findByMenuId(menuId)
                .orElseThrow(() -> new ControlledException(MENU_NOT_FOUND));

        return menu;
    }

    public List<Menu> getAllMenu(Long restaurantId) {
        var restaurant = restaurantService.getRestaurant(restaurantId);
        var menus = menuRepository.findByRestaurant(restaurant)
                .orElseThrow(() -> new ControlledException(MENU_NOT_FOUND));
        return menus;
    }
}
