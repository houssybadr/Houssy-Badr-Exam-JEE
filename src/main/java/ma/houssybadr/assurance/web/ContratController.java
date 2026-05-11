package ma.houssybadr.assurance.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.houssybadr.assurance.dtos.ContratRequest;
import ma.houssybadr.assurance.dtos.ContratResponse;
import ma.houssybadr.assurance.enums.StatutContrat;
import ma.houssybadr.assurance.services.IContratService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Tag(name = "Contrats", description = "Gestion des contrats d'assurance")
public class ContratController {

    private final IContratService contratService;

    @GetMapping
    @Operation(summary = "Lister tous les contrats")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<List<ContratResponse>> getAllContrats() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir un contrat par ID")
    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYE','ADMIN')")
    public ResponseEntity<ContratResponse> getContrat(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.getContrat(id));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Contrats d'un client")
    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYE','ADMIN')")
    public ResponseEntity<List<ContratResponse>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(contratService.getContratsByClient(clientId));
    }

    @GetMapping("/statut/{statut}")
    @Operation(summary = "Contrats filtrés par statut")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<List<ContratResponse>> getByStatut(@PathVariable StatutContrat statut) {
        return ResponseEntity.ok(contratService.getContratsByStatut(statut));
    }

    @PostMapping
    @Operation(summary = "Souscrire un nouveau contrat")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<ContratResponse> createContrat(@Valid @RequestBody ContratRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.creerContrat(request));
    }

    @PutMapping("/{id}/valider")
    @Operation(summary = "Valider un contrat EN_COURS")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<ContratResponse> valider(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.validerContrat(id));
    }

    @PutMapping("/{id}/resilier")
    @Operation(summary = "Résilier un contrat")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<ContratResponse> resilier(@PathVariable Long id) {
        return ResponseEntity.ok(contratService.resilierContrat(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long id) {
        contratService.supprimerContrat(id);
        return ResponseEntity.noContent().build();
    }
}
