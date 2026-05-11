package ma.houssybadr.assurance.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.houssybadr.assurance.enums.NiveauCouverture;

/**
 * Contrat d'assurance santé.
 */
@Entity
@DiscriminatorValue("SANTE")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContratAssuranceSante extends ContratAssurance {

    @Enumerated(EnumType.STRING)
    @Column(name = "niveau_couverture")
    private NiveauCouverture niveauCouverture;

    @Column(name = "nb_personnes_couvertes")
    private Integer nbPersonnesCouvertes;
}
