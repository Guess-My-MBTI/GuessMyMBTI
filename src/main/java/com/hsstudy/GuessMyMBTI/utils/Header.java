package com.hsstudy.GuessMyMBTI.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// Body에 header 감싸는?

@Data
@AllArgsConstructor
@Builder
public class Header<T> {
    private String resultCode;
    private String errorMessage;
    private T data;

    public static <T> Header<T> SUCCESS(T data) {
        return (Header<T>) Header.builder()
                .resultCode("00")
                .data(data)
                .build();
    }

    public static <T> Header<T> FAIL(Exception e) {
        return (Header<T>) Header.builder()
                .resultCode("99")
                .errorMessage(e.getMessage())
                .build();
    }

}
