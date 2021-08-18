package com.gabriel.afonso.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Problem {

    private String message;
    private Long status_code;

}
