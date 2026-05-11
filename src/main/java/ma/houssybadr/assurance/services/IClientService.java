package ma.houssybadr.assurance.services;

import ma.houssybadr.assurance.dtos.ClientRequest;
import ma.houssybadr.assurance.dtos.ClientResponse;

import java.util.List;

public interface IClientService {
    ClientResponse creerClient(ClientRequest request);
    ClientResponse getClient(Long id);
    List<ClientResponse> getAllClients();
    List<ClientResponse> rechercherParNom(String nom);
    ClientResponse mettreAJour(Long id, ClientRequest request);
    void supprimerClient(Long id);
}
