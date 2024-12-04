package com.project.api.notice.message.send.option;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 대체하고 싶은 내용을 지정할 수 있다.
 *
 * 각 항목은 [*n*] 문구에 치환된다.
 */
@Setter
@Getter
@Builder
public class ChangeWord {
    @Builder.Default
    private String var1 = "null";
    @Builder.Default
    private String var2 = "null";
    @Builder.Default
    private String var3 = "null";
    @Builder.Default
    private String var4 = "null";
    @Builder.Default
    private String var5 = "null";
    @Builder.Default
    private String var6 = "null";
    @Builder.Default
    private String var7 = "null";
}
