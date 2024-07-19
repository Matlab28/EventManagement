package com.example.simpleeventmanager.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserRequestDto {
    private String emailFrom;
    private List<String> emailTo;
    private String subject;
    private String message;
}
