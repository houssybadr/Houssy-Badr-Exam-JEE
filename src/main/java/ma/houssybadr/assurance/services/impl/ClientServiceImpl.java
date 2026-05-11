package ma.houssybadr.assurance.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.houssybadr.assurance.dtos.ClientRequest;
import ma.houssybadr.assurance.dtos.ClientResponse;
import ma.houssybadr.assurance.entities.Client;
import ma.houssybadr.assurance.mappers.ClientMapper;
import ma.houssybadr.assurance.repositories.ClientRepository;
import ma.houssybadr.assurance.services.IClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientResponse creerClient(ClientRequest request) {
        if (clientRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Un client avec l'email " + request.email() + " existe déjà");
        }
        Client client = clientMapper.toEntity(request);
        Client saved = clientRepository.save(client);
        log.info("Client créé : {}", saved.getId());
        return clientMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ClientResponse getClient(Long id) {
        Client client = clientRepository.findByIdWithContrats(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));
        return clientMapper.toResponse(client);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClientResponse> rechercherParNom(String nom) {
        return clientRepository.findByNomContainingIgnoreCase(nom).stream()
                .map(clientMapper::toResponse)
                .toList();
    }

    @Override
    public ClientResponse mettreAJour(Long id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + id));
        client.setNom(request.nom());
        client.setEmail(request.email());
        return clientMapper.toResponse(clientRepository.save(client));
    }

    @Override
    public void supprimerClient(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new RuntimeException("Client introuvable : " + id);
        }
        clientRepository.deleteById(id);
        log.info("Client supprimé : {}", id);
    }
}
