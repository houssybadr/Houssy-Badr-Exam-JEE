package ma.houssybadr.assurance.mappers;

import ma.houssybadr.assurance.dtos.PaiementResponse;
import ma.houssybadr.assurance.entities.Paiement;
import org.springframework.stereotype.Component;

@Component
public class PaiementMapper {

    public PaiementResponse toResponse(Paiement paiement) {
        String typeContrat = paiement.getContrat().getClass()
                .getAnnotation(jakarta.persistence.DiscriminatorValue.class).value();
        return new PaiementResponse(
                paiement.getId(),
                paiement.getDatePaiement(),
                paiement.getMontant(),
                paiement.getType(),
                paiement.getContrat().getId(),
                typeContrat
        );
    }
}
