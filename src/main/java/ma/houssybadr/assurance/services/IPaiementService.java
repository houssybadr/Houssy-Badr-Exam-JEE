package ma.houssybadr.assurance.services;

import ma.houssybadr.assurance.dtos.PaiementRequest;
import ma.houssybadr.assurance.dtos.PaiementResponse;

import java.util.List;

public interface IPaiementService {
    PaiementResponse enregistrerPaiement(PaiementRequest request);
    PaiementResponse getPaiement(Long id);
    List<PaiementResponse> getPaiementsByContrat(Long contratId);
    List<PaiementResponse> getPaiementsByClient(Long clientId);
    Double getTotalPaiementsContrat(Long contratId);
    void supprimerPaiement(Long id);
}
