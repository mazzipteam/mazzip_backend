package com.project.api.notice.message.send.messagetype;

import com.project.api.notice.message.PpurioMessageDTO;
import com.project.api.notice.message.send.option.Target;
import com.project.exception.ControlledException;

import java.util.List;

import static com.project.exception.error_code.PpurioErrorCode.CONTENT_IS_TOO_LONG;

/**
 * 짧 메세지를 전달하는 클래스이다.
 * 90byte 이하 메세지 용량에서 사용가능하다.
 * 장문의 경우 LMS, 이미지를 전송할 시 MMS로 타입을 전송할 것
 *
 */
public class SMS extends MessageType {
    public SMS(String ppurioAccount, PpurioMessageDTO ppurioMessageDTO, List<Target> targets) {
        super(ppurioAccount, ppurioMessageDTO.getContent(), targets);

        // 문자 메세지 길이 파악
        if(ppurioMessageDTO.getContent().getBytes().length > 90) throw new ControlledException(CONTENT_IS_TOO_LONG);
    }
}
