package com.project.api.notice.message.send.option;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 전송할 대상 및 대상에 대한 추가 속성에 관한 클래스
 */
@Setter
@Getter
@Builder
public class Target {
    // 전송할 대상의 연락처
    private final String toNumber;

    // PpurioSendParam.contents의 내용 중 [*이름*]과 대치될 문구
    @Builder.Default
    private final String name = "이름";

    // PpurioSendParam.changeWord의 내용 중 [*n*]과 대치될 문구 (※ 해당 클래스 주석 참고)
    @Builder.Default
    private final ChangeWord changeWord = ChangeWord.builder().build();

    /**
     * 해당 클래스를 Map 형식으로 반환
     *
     * @return to, name, changeWord를 Map 형식으로 반환
     */
    @JsonValue
    public Map<String, Object> get() {
        Map<String, Object> map = new HashMap<>();

        System.out.println(toNumber);
        map.put("to", toNumber);
        map.put("name", name);
        map.put("changeWord", changeWord);

        return map;
    }
}
