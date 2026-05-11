package ma.houssybadr.assurance.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ma.houssybadr.assurance.enums.NiveauCouverture;
import ma.houssybadr.assurance.enums.TypeLogement;

import java.time.LocalDate;

/**
 * DTO de création/mise à jour pour tous les sous-types de contrat.
 * Les champs spécifiques sont optionnels selon le type.
 */
public record ContratRequest(

        @NotNull(message = "La date de souscription est obligatoire")
        LocalDate dateSouscription,

        @NotNull @Positive
        Double montantCotisation,

        @NotNull @Positive
        Integer dureeContrat,

        @NotNull @Positive
        Double tauxCouverture,

        @NotNull(message = "L'id client est obligatoire")
        Long clientId,

        // Discriminant : AUTO | HABITATION | SANTE
        @NotNull
        String typeContrat,

        // ── Champs AUTO ──────────────────────────────
        String numImmatriculation,
        String marqueVehicule,
        String modeleVehicule,

        // ── Champs HABITATION ────────────────────────
        TypeLogement typeLogement,
        String adresseLogement,
        Double superficie,

        // ── Champs SANTE ─────────────────────────────
        NiveauCouverture niveauCouverture,
        Integer nbPersonnesCouvertes
) {}
