package ma.houssybadr.assurance.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * Contrat d'assurance automobile.
 */
@Entity
@DiscriminatorValue("AUTO")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ContratAssuranceAuto extends ContratAssurance {

    @Column(name = "num_immatriculation")
    private String numImmatriculation;

    @Column(name = "marque_vehicule")
    private String marqueVehicule;

    @Column(name = "modele_vehicule")
    private String modeleVehicule;
}
