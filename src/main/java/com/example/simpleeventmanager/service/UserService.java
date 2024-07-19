package com.example.simpleeventmanager.service;

import com.example.simpleeventmanager.dto.request.UserRequestDto;
import com.example.simpleeventmanager.dto.response.UserResponseDto;
import com.example.simpleeventmanager.entity.UserEntity;
import com.example.simpleeventmanager.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository repository;
    private final JavaMailSender javaMailSender;

    public String manage(UserRequestDto dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);
//        String regex = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(gmail|mail|icloud|yahoo|yandex|mail)\\.com$";
//
//        if (!dto.getEmailFrom().matches(regex)) {
//            log.error("Invalid email - " + dto.getEmailFrom());
//            return "Invalid email";
//        }
//
//        Pattern pattern = Pattern.compile(regex);
//
//        List<String> emails = dto.getEmailTo();
//        if (emails == null) {
//            emails = new ArrayList<>();
//        }
//
//        for (String url : emails) {
//            Matcher matcher = pattern.matcher(url);
//            if (!matcher.matches()) {
//                log.error("Invalid email.");
//                return "Invalid send to email...";
//            } else {
//                log.info("Valid.");
//            }
//        }

        sendEmail(dto);
        repository.save(entity);
        return "Message sent.";
    }

    public void sendEmail(UserRequestDto dto) {
        UserEntity entity = modelMapper.map(dto, UserEntity.class);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(dto.getEmailFrom().toLowerCase());

            String[] emailToArray = dto.getEmailTo().toArray(new String[0]);
            emailToArray = Arrays.stream(emailToArray)
                    .map(String::toLowerCase)
                    .toArray(String[]::new);

            helper.setTo(emailToArray);
            helper.setSubject(dto.getSubject());
            helper.setText(dto.getMessage());

            // Attach file if present
            if (dto.getAttachment() != null && !dto.getAttachment().isEmpty()) {
                helper.addAttachment(dto.getAttachment().getOriginalFilename(), dto.getAttachment());
            }

            javaMailSender.send(message);
            log.info("Message sent from - " + dto.getEmailFrom().toLowerCase() + ", to - "
                    + String.join(", ", dto.getEmailTo()).toLowerCase());
        } catch (MessagingException e) {
            log.error("Error sending email: " + e.getMessage(), e);
        }

        repository.save(entity);
    }


//    public void sendEmail(UserRequestDto dto) {
//        UserEntity entity = modelMapper.map(dto, UserEntity.class);
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom(dto.getEmailFrom().toLowerCase());
//
//
//        String[] emailToArray = dto.getEmailTo().toArray(new String[0]);
//
//        emailToArray = Arrays.stream(emailToArray)
//                .map(String::toLowerCase)
//                .toArray(String[]::new);
//
//        message.setTo(emailToArray);
//
//        message.setText(dto.getMessage());
//        message.setSubject(dto.getSubject());
//        javaMailSender.send(message);
//
//        log.info("Message sent from - " + dto.getEmailFrom().toLowerCase() + ", to - "
//                + String.join(", ", dto.getEmailTo()).toLowerCase());
//        repository.save(entity);
//    }

    public List<UserResponseDto> readAll() {
        log.info("All user info responded");
        return repository
                .findAll()
                .stream()
                .map(m -> modelMapper.map(m, UserResponseDto.class))
                .collect(Collectors.toList());
    }
}
