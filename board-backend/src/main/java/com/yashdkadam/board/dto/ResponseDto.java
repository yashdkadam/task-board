package com.yashdkadam.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ResponseDto {
    private String statusCode;

    private Object statusMsg;
}
