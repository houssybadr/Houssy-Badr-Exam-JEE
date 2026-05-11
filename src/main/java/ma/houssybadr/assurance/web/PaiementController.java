package ma.houssybadr.assurance.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.houssybadr.assurance.dtos.PaiementRequest;
import ma.houssybadr.assurance.dtos.PaiementResponse;
import ma.houssybadr.assurance.services.IPaiementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paiements")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@Tag(name = "Paiements", description = "Gestion des paiements de contrats")
public class PaiementController {

    private final IPaiementService paiementService;

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Paiements d'un contrat")
    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYE','ADMIN')")
    public ResponseEntity<List<PaiementResponse>> getByContrat(@PathVariable Long contratId) {
        return ResponseEntity.ok(paiementService.getPaiementsByContrat(contratId));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Tous les paiements d'un client")
    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYE','ADMIN')")
    public ResponseEntity<List<PaiementResponse>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(paiementService.getPaiementsByClient(clientId));
    }

    @GetMapping("/contrat/{contratId}/total")
    @Operation(summary = "Total des paiements d'un contrat")
    @PreAuthorize("hasAnyRole('CLIENT','EMPLOYE','ADMIN')")
    public ResponseEntity<Map<String, Double>> getTotal(@PathVariable Long contratId) {
        return ResponseEntity.ok(Map.of("total", paiementService.getTotalPaiementsContrat(contratId)));
    }

    @PostMapping
    @Operation(summary = "Enregistrer un paiement")
    @PreAuthorize("hasAnyRole('EMPLOYE','ADMIN')")
    public ResponseEntity<PaiementResponse> createPaiement(@Valid @RequestBody PaiementRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paiementService.enregistrerPaiement(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un paiement")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePaiement(@PathVariable Long id) {
        paiementService.supprimerPaiement(id);
        return ResponseEntity.noContent().build();
    }
}
