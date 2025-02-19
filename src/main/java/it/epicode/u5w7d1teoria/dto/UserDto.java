package it.epicode.u5w7d1teoria.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String surname;
    @Email
    @NotBlank(message = "L'email non può essere vuota o mancante o composta da soli spazi")
    private String email;
    @NotBlank(message = "La password non può essere vuota o mancante o composta da soli spazi")
    private String password;
}
