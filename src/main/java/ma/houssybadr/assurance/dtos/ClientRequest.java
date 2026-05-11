package ma.houssybadr.assurance.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClientRequest(
        @NotBlank(message = "Le nom est obligatoire")
        String nom,

        @Email(message = "Format email invalide")
        @NotBlank(message = "L'email est obligatoire")
        String email
) {}
