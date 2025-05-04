package com.example.WebApplicationOrderManagmentSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateDto {
    @NotBlank(message = "Логин не может быть пустым")
    @Size(min = 3, max = 50, message = "Логин должен быть от 3 до 50 символов")
    private String login;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный email")
    private String email;

    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String newPassword;

    public @NotBlank(message = "Логин не может быть пустым") @Size(min = 3, max = 50, message = "Логин должен быть от 3 до 50 символов") String getLogin() {
        return login;
    }

    public void setLogin(@NotBlank(message = "Логин не может быть пустым") @Size(min = 3, max = 50, message = "Логин должен быть от 3 до 50 символов") String login) {
        this.login = login;
    }

    public @NotBlank(message = "Email не может быть пустым") @Email(message = "Некорректный email") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email не может быть пустым") @Email(message = "Некорректный email") String email) {
        this.email = email;
    }

    public @Size(min = 6, message = "Пароль должен быть не менее 6 символов") String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(@Size(min = 6, message = "Пароль должен быть не менее 6 символов") String newPassword) {
        this.newPassword = newPassword;
    }
}
