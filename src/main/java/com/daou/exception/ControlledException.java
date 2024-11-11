package com.daou.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ControlledException extends RuntimeException {
    private final ErrorMessage errorCode;
}