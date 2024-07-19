package com.example.simpleeventmanager.controller;

import com.example.simpleeventmanager.dto.request.UserRequestDto;
import com.example.simpleeventmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://matlab28.github.io")
public class UserController {
    private final UserService service;

    @PostMapping("/send-email")
    public String sendEmail(
            @RequestParam("emailFrom") String emailFrom,
            @RequestParam("emailTo") List<String> emailTo,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment
    ) {
        UserRequestDto dto = new UserRequestDto();
        dto.setEmailFrom(emailFrom);
        dto.setEmailTo(emailTo);
        dto.setSubject(subject);
        dto.setMessage(message);
        dto.setAttachment(attachment);

        return service.manage(dto);
    }

//    @PostMapping("/send-email")
//    public String manage(@RequestBody UserRequestDto dto) {
//        return service.manage(dto);
//    }
}


//
//@GetMapping("/read-all")
//public List<UserResponseDto> readAll() {
//    return service.readAll();
//}