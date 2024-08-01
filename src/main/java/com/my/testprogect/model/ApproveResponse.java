package com.my.testprogect.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveResponse {
    private boolean approved;
    private String message;
    private Long userId;
}
