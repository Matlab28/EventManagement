package com.example.simpleeventmanager.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserResponseDto {
    private Long id;
    private String emailFrom;
    private List<String> emailTo;
    private String subject;
    private String message;
}
