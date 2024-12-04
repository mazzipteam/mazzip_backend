package com.project.api.notice.message;

import com.project.api.bookmark.BookmarkService;
import com.project.api.notice.NoticeCreateDTO;
import com.project.api.notice.NoticeService;
import com.project.api.notice.message.auth.PpurioAuth;
import com.project.api.notice.message.send.PpurioSendResponse;
import com.project.api.notice.message.send.messagetype.SMS;
import com.project.api.notice.message.send.option.Target;
import com.project.api.restaurant.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PpurioSendService {
    private final RestTemplate restTemplate;
    private final RestaurantService restaurantService;
    private final BookmarkService bookmarkService;
    private final NoticeService noticeService;
    // 뿌리오 계정
    @Value("${spring.ppurio.account}")
    private String ppurioAccount;

    // 뿌리오 API 이용을 위한 고정 주소
    @Value("${spring.ppurio.base-url}")
    private String url;

    private final PpurioAuth ppurioAuth;

    public PpurioSendResponse message(PpurioMessageDTO ppurioMessageDTO) {
        // 토큰 발급
        String accessToken = ppurioAuth.getAccessToken();

        // 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer "+ accessToken);

        // 바디 설정
        List<Target> targets = new ArrayList<>();
        var bookmarks = bookmarkService.getAllBookmarkByRestaurant(ppurioMessageDTO.getRestaurantId());
        bookmarks.forEach(bookmark -> targets.add(Target.builder().toNumber(bookmark.getUser().getTelNum()).build()));

        var requestBody = new SMS(ppurioAccount, ppurioMessageDTO, targets).getParams();

        // 전송 데이터 생성
        HttpEntity<Map> request = new HttpEntity<>(requestBody, headers);

        // 전송
        var ppurioSendResponse = restTemplate.postForObject(url+"/v1/message", request, PpurioSendResponse.class);

        for(var bookmark : bookmarks) {
            var noticeCreateDTO = NoticeCreateDTO.builder()
                    .bookmark(bookmark)
                    .message(ppurioMessageDTO.getContent())
                    .build();
            noticeService.create(noticeCreateDTO);
        }

        // 메세지 저장
        return ppurioSendResponse;
    }
}
