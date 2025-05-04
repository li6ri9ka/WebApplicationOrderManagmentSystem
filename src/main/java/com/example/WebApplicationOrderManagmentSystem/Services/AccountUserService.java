package com.example.WebApplicationOrderManagmentSystem.Services;

import com.example.WebApplicationOrderManagmentSystem.DTO.*;
import com.example.WebApplicationOrderManagmentSystem.JwtUtils;
import com.example.WebApplicationOrderManagmentSystem.Model.AccountUser;
import com.example.WebApplicationOrderManagmentSystem.Model.RoleUser;
import com.example.WebApplicationOrderManagmentSystem.Repositories.AccountUserRepository;
import com.example.WebApplicationOrderManagmentSystem.Repositories.RoleUserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountUserService implements UserDetailsService {

    @Autowired
    private AccountUserRepository accountUserRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private MappingUntils mappingUntils;

    @Autowired
    private RoleUserRepository roleUserRepository;


    public AccountUser registerUser(AccountUser accountUser) {
        RoleUser defaultRoleUser = roleUserRepository.findById(2L).orElse(null);
        accountUser.setRole(defaultRoleUser);
        String encodedPassword = passwordEncoder.encode(accountUser.getPassword());
        accountUser.setPassword(encodedPassword);
        return accountUserRepository.save(accountUser);
    }

    public AuthResponseDTO authenticateUser(SingInDTO singIn) {
        AccountUser user = accountUserRepository.findByLogin(singIn.getLogin());


        if (user == null || !passwordEncoder.matches(singIn.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid login or password");
        }
        String token = jwtUtils.generateToken(user.getUsername(), user.getRole().getRole(), user.getId());

        return new AuthResponseDTO(
                user.getRole(),
                user.getLogin(),
                token
        );
    }

    public List<UserDTO> getAllUsers() {
        return accountUserRepository.findAll().stream().map(mappingUntils::mappingAccountUserDTO).toList();
    }

    public AccountUser findById(Long id) {
        return accountUserRepository.findById(id).orElse(null);
    }

    @Transactional
    public AccountUser updateUser(Long id, UserUpdateDto updateDto) {
        AccountUser existingUser = accountUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));


        existingUser.setLogin(updateDto.getLogin());
        existingUser.setEmail(updateDto.getEmail());


        if (updateDto.getNewPassword() != null && !updateDto.getNewPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updateDto.getNewPassword()));
        }

        return accountUserRepository.save(existingUser);
    }


    public void deleteUser(Long id) {
        if(accountUserRepository.findById(id).isPresent()) {
            accountUserRepository.deleteById(id);
        }
        else{
            throw new RuntimeException("User with id " + id + " not found");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountUserRepository.findByLogin(username);
    }
}

