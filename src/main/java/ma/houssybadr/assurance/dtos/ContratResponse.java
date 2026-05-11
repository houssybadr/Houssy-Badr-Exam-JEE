package ma.houssybadr.assurance.dtos;

import ma.houssybadr.assurance.enums.NiveauCouverture;
import ma.houssybadr.assurance.enums.StatutContrat;
import ma.houssybadr.assurance.enums.TypeLogement;

import java.time.LocalDate;

public record ContratResponse(
        Long id,
        String typeContrat,
        LocalDate dateSouscription,
        StatutContrat statut,
        LocalDate dateValidation,
        Double montantCotisation,
        Integer dureeContrat,
        Double tauxCouverture,
        Long clientId,
        String clientNom,

        // AUTO
        String numImmatriculation,
        String marqueVehicule,
        String modeleVehicule,

        // HABITATION
        TypeLogement typeLogement,
        String adresseLogement,
        Double superficie,

        // SANTE
        NiveauCouverture niveauCouverture,
        Integer nbPersonnesCouvertes
) {}
