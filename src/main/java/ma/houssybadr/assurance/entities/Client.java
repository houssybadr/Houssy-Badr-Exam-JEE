package ma.houssybadr.assurance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un client pouvant souscrire plusieurs contrats d'assurance.
 */
@Entity
@Table(name = "clients")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Column(nullable = false)
    private String nom;

    @Email(message = "Format email invalide")
    @NotBlank(message = "L'email est obligatoire")
    @Column(nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<ContratAssurance> contrats = new ArrayList<>();
}
