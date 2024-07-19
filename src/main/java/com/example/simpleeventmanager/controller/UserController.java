package com.example.simpleeventmanager.controller;

import com.example.simpleeventmanager.dto.request.UserRequestDto;
import com.example.simpleeventmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/send-email")
    public String manage(@RequestBody UserRequestDto dto) {
        return service.manage(dto);
    }
}


//
//@GetMapping("/read-all")
//public List<UserResponseDto> readAll() {
//    return service.readAll();
//}