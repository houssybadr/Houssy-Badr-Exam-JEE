package ma.houssybadr.assurance.mappers;

import ma.houssybadr.assurance.dtos.ClientRequest;
import ma.houssybadr.assurance.dtos.ClientResponse;
import ma.houssybadr.assurance.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientRequest req) {
        return Client.builder()
                .nom(req.nom())
                .email(req.email())
                .build();
    }

    public ClientResponse toResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getNom(),
                client.getEmail(),
                client.getContrats().size()
        );
    }
}
