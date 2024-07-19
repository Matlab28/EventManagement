package com.example.simpleeventmanager.controller;

import com.example.simpleeventmanager.dto.request.UserRequestDto;
import com.example.simpleeventmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://matlab28.github.io")
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