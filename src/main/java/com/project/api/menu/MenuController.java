package com.project.api.menu;

import com.project.api.menu.dto.MenuCreateDTO;
import com.project.api.menu.dto.MenuUpdateDTO;
import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    // 메뉴 생성
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity create(
            @RequestPart MenuCreateDTO menuCreateDTO,
            @RequestPart(value = "multipartFile", required = false) MultipartFile multipartFile
    ) {
        var menu = menuService.create(menuCreateDTO, multipartFile);
        var response = CommonResponse.builder().code(200).message("메뉴 생성 성공").data(menu).build();
        return ResponseEntity.ok(response);
    }

    // 메뉴 수정
    @PatchMapping
    public ResponseEntity update(@RequestBody MenuUpdateDTO menuUpdateDTO) {
        var menu = menuService.update(menuUpdateDTO);
        var response = CommonResponse.builder().code(200).message("메뉴 수정 성공").data(menu).build();
        return ResponseEntity.ok(response);
    }

    // 메뉴 삭제
    @DeleteMapping("/{menuId}")
    public ResponseEntity delete(@PathVariable Long menuId) {
        var menu = menuService.delete(menuId);
        var response = CommonResponse.builder().code(200).message("메뉴 삭제 성공").data(menu).build();
        return ResponseEntity.ok(response);
    }

    // 메뉴 조회
    @GetMapping("/{menuId}")
    public ResponseEntity get(@PathVariable Long menuId) {
        var menu = menuService.getMenu(menuId);
        var response = CommonResponse.builder().code(200).message("메뉴 조회 성공").data(menu).build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{restaurantId}")
    public ResponseEntity getAll(@PathVariable Long restaurantId) {
        var menus = menuService.getAllMenu(restaurantId);
        var response = CommonResponse.builder().code(200).message("메뉴 조회 성공").data(menus).build();
        return ResponseEntity.ok(response);
    }
}
