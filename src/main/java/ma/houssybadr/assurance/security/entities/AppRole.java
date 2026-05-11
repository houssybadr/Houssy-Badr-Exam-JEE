package ma.houssybadr.assurance.security.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_roles")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roleName;
}
