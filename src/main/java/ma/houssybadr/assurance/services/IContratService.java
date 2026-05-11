package ma.houssybadr.assurance.services;

import ma.houssybadr.assurance.dtos.ContratRequest;
import ma.houssybadr.assurance.dtos.ContratResponse;
import ma.houssybadr.assurance.enums.StatutContrat;

import java.util.List;

public interface IContratService {
    ContratResponse creerContrat(ContratRequest request);
    ContratResponse getContrat(Long id);
    List<ContratResponse> getAllContrats();
    List<ContratResponse> getContratsByClient(Long clientId);
    List<ContratResponse> getContratsByStatut(StatutContrat statut);
    ContratResponse validerContrat(Long id);
    ContratResponse resilierContrat(Long id);
    void supprimerContrat(Long id);
}
