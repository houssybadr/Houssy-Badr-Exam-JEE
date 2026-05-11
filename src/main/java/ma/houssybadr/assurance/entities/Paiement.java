package ma.houssybadr.assurance.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.houssybadr.assurance.enums.TypePaiement;

import java.time.LocalDate;

/**
 * Paiement associé à un contrat d'assurance.
 */
@Entity
@Table(name = "paiements")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate datePaiement;

    @Column(nullable = false)
    private Double montant;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypePaiement type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrat_id", nullable = false)
    private ContratAssurance contrat;
}
