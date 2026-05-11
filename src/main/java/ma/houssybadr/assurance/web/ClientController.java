package ma.houssybadr.assurance.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.houssybadr.assurance.dtos.ClientRequest;
import ma.houssybadr.assurance.dtos.ClientResponse;
import ma.houssybadr.assurance.services.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "Gestion des clients")
public class ClientController {

    private final IClientService clientService;

    @GetMapping
    @Operation(summary = "Lister tous les clients")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un client par ID")
    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYE','ADMIN')")
    public ResponseEntity<ClientResponse> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Rechercher des clients par nom")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<List<ClientResponse>> search(@RequestParam String nom) {
        return ResponseEntity.ok(clientService.rechercherParNom(nom));
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau client")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.creerClient(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Mettre à jour un client")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<ClientResponse> updateClient(@PathVariable Long id,
                                                        @Valid @RequestBody ClientRequest request) {
        return ResponseEntity.ok(clientService.mettreAJour(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.supprimerClient(id);
        return ResponseEntity.noContent().build();
    }
}
