package com.example.WebApplicationOrderManagmentSystem.Controller;

import com.example.WebApplicationOrderManagmentSystem.DTO.SingUpDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.UserDTO;
import com.example.WebApplicationOrderManagmentSystem.DTO.UserUpdateDto;
import com.example.WebApplicationOrderManagmentSystem.Model.AccountUser;
import com.example.WebApplicationOrderManagmentSystem.Repositories.AccountUserRepository;
import com.example.WebApplicationOrderManagmentSystem.Services.AccountUserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AccountUserService accountUserService;

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserDTO> getAllUser() {
        return accountUserService.getAllUsers();
    }

    @GetMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AccountUser> getUserById(@PathVariable Long id) {
        AccountUser accountUser = accountUserService.findById(id);
        if (accountUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountUser);
    }

    @GetMapping("/updateUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<UserUpdateDto> getUserForUpdate(@PathVariable Long id) {
        AccountUser accountUser = accountUserService.findById(id);
        if (accountUser == null) {
            return ResponseEntity.notFound().build();
        }
        UserUpdateDto dto = new UserUpdateDto();
        dto.setLogin(accountUser.getLogin());
        dto.setEmail(accountUser.getEmail());

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/updateUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDto updateDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest()
                    .body(bindingResult.getAllErrors()
                            .stream()
                            .map(DefaultMessageSourceResolvable::getDefaultMessage)
                            .collect(Collectors.joining(", ")));
        }

        try {
            AccountUser updatedUser = accountUserService.updateUser(id, updateDto);
            return ResponseEntity.ok(updatedUser);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Object getUserForDelete(@PathVariable Long id) {
        AccountUser accountUserToDelete = accountUserService.findById(id);
        if (accountUserToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(accountUserToDelete);
    }

    @DeleteMapping("/deleteUser/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        AccountUser accountUserToDelete = accountUserService.findById(id);
        if (accountUserToDelete == null) {
            return ResponseEntity.notFound().build();
        }
        accountUserService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/updateAccount/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {

        AccountUser accountUser = accountUserRepository.findById(id).orElse(null);
        if (accountUser == null) {
            return ResponseEntity.notFound().build();
        }

        accountUser.setLogin(userDTO.getLogin());
        accountUser.setEmail(userDTO.getEmail());

        if (userDTO.getPassword() != null && !userDTO.getPassword().isBlank()) {
            accountUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        accountUserRepository.save(accountUser);
        return ResponseEntity.ok("Профиль обновлён");
    }





}
