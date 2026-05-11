package ma.houssybadr.assurance.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.houssybadr.assurance.enums.TypeLogement;

/**
 * Contrat d'assurance habitation.
 */
@Entity
@DiscriminatorValue("HABITATION")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContratAssuranceHabitation extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    @Column(name = "type_logement")
    private TypeLogement typeLogement;

    @Column(name = "adresse_logement")
    private String adresseLogement;

    /** Superficie en m² */
    private Double superficie;
}
