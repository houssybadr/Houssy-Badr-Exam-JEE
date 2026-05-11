package ma.houssybadr.assurance.mappers;

import ma.houssybadr.assurance.dtos.ContratResponse;
import ma.houssybadr.assurance.entities.*;
import org.springframework.stereotype.Component;

@Component
public class ContratMapper {

    public ContratResponse toResponse(ContratAssurance contrat) {
        String type = contrat.getClass().getAnnotation(
                jakarta.persistence.DiscriminatorValue.class).value();

        String numImmat = null, marque = null, modele = null;
        ma.houssybadr.assurance.enums.TypeLogement typeLogement = null;
        String adresse = null;
        Double superficie = null;
        ma.houssybadr.assurance.enums.NiveauCouverture niveauCouverture = null;
        Integer nbPersonnes = null;

        if (contrat instanceof ContratAssuranceAuto a) {
            numImmat = a.getNumImmatriculation();
            marque   = a.getMarqueVehicule();
            modele   = a.getModeleVehicule();
        } else if (contrat instanceof ContratAssuranceHabitation h) {
            typeLogement = h.getTypeLogement();
            adresse      = h.getAdresseLogement();
            superficie   = h.getSuperficie();
        } else if (contrat instanceof ContratAssuranceSante s) {
            niveauCouverture = s.getNiveauCouverture();
            nbPersonnes      = s.getNbPersonnesCouvertes();
        }

        return new ContratResponse(
                contrat.getId(),
                type,
                contrat.getDateSouscription(),
                contrat.getStatut(),
                contrat.getDateValidation(),
                contrat.getMontantCotisation(),
                contrat.getDureeContrat(),
                contrat.getTauxCouverture(),
                contrat.getClient().getId(),
                contrat.getClient().getNom(),
                numImmat, marque, modele,
                typeLogement, adresse, superficie,
                niveauCouverture, nbPersonnes
        );
    }
}
