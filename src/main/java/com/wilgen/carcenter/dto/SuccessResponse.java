package com.wilgen.carcenter.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SuccessResponse<T> extends Response{
    T result;

    public SuccessResponse(String message, String status, T result) {
        super(message, status);
        this.result = result;
    }
}
