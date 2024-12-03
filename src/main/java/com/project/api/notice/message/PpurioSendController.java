package com.project.api.notice.message;

import com.project.common.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ppurio/")
@RequiredArgsConstructor
public class PpurioSendController {
    private final PpurioSendService ppurioSendService;

    @PostMapping(value = "message")
    public CommonResponse message(
            @RequestBody PpurioMessageDTO ppurioMessageDTO) {

        var response = ppurioSendService.message(ppurioMessageDTO);
        return CommonResponse.builder().code(200).message("문자 전송 성공").data(response).build();
    }
}
