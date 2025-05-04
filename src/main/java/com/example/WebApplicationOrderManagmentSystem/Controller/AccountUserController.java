package com.example.WebApplicationOrderManagmentSystem.Controller;

import com.example.WebApplicationOrderManagmentSystem.DTO.AuthResponseDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.SingInDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.SingUpDTO;
import com.example.WebApplicationOrderManagmentSystem.JwtUtils;
import com.example.WebApplicationOrderManagmentSystem.Model.AccountUser;
import com.example.WebApplicationOrderManagmentSystem.Repositories.AccountUserRepository;
import com.example.WebApplicationOrderManagmentSystem.Services.AccountUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AccountUserController {

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SingInDTO dto) {
        try {
            AuthResponseDTO authResponse = accountUserService.authenticateUser(dto);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Ошибка аутентификации: " + e.getMessage());
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AccountUser accountUser) {
        try {
            if (accountUserRepository.existsByLogin(accountUser.getLogin())) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Пользователь с таким логином уже существует");
            }
            AccountUser registeredUser = accountUserService.registerUser(accountUser);

            String token = jwtUtils.generateToken(
                    registeredUser.getUsername(),
                    registeredUser.getRole().getRole(),
                    registeredUser.getId()
            );
            AuthResponseDTO authResponseDTO = new AuthResponseDTO(registeredUser.getRole(),registeredUser.getLogin(),token);

            return ResponseEntity.status(HttpStatus.CREATED).body(authResponseDTO);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Ошибка при регистрации: " + e.getMessage());
        }
    }

}
