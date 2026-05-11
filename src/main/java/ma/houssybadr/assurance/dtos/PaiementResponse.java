package ma.houssybadr.assurance.dtos;

import ma.houssybadr.assurance.enums.TypePaiement;

import java.time.LocalDate;

public record PaiementResponse(
        Long id,
        LocalDate datePaiement,
        Double montant,
        TypePaiement type,
        Long contratId,
        String typeContrat
) {}
