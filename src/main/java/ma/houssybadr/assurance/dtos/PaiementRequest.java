package ma.houssybadr.assurance.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ma.houssybadr.assurance.enums.TypePaiement;

import java.time.LocalDate;

public record PaiementRequest(
        @NotNull LocalDate datePaiement,
        @NotNull @Positive Double montant,
        @NotNull TypePaiement type,
        @NotNull Long contratId
) {}
