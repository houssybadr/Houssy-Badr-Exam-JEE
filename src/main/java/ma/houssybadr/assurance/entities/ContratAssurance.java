package ma.houssybadr.assurance.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import ma.houssybadr.assurance.enums.StatutContrat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entité abstraite centrale pour tous les types de contrats d'assurance.
 * Stratégie SINGLE_TABLE : toutes les sous-classes dans une seule table.
 */
@Entity
@Table(name = "contrats_assurance")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_CONTRAT", discriminatorType = DiscriminatorType.STRING)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public abstract class ContratAssurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateSouscription;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutContrat statut = StatutContrat.EN_COURS;

    private LocalDate dateValidation;

    @Column(nullable = false)
    private Double montantCotisation;

    /** Durée du contrat en mois */
    @Column(nullable = false)
    private Integer dureeContrat;

    /** Taux de couverture en pourcentage (0-100) */
    @Column(nullable = false)
    private Double tauxCouverture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @JsonIgnore
    @OneToMany(mappedBy = "contrat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Paiement> paiements = new ArrayList<>();
}
