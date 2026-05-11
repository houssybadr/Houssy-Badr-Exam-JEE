package ma.houssybadr.assurance.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.houssybadr.assurance.dtos.ContratRequest;
import ma.houssybadr.assurance.dtos.ContratResponse;
import ma.houssybadr.assurance.entities.*;
import ma.houssybadr.assurance.enums.StatutContrat;
import ma.houssybadr.assurance.mappers.ContratMapper;
import ma.houssybadr.assurance.repositories.ClientRepository;
import ma.houssybadr.assurance.repositories.ContratAssuranceRepository;
import ma.houssybadr.assurance.services.IContratService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ContratServiceImpl implements IContratService {

    private final ContratAssuranceRepository contratRepository;
    private final ClientRepository clientRepository;
    private final ContratMapper contratMapper;

    @Override
    public ContratResponse creerContrat(ContratRequest req) {
        Client client = clientRepository.findById(req.clientId())
                .orElseThrow(() -> new RuntimeException("Client introuvable : " + req.clientId()));

        ContratAssurance contrat = switch (req.typeContrat().toUpperCase()) {
            case "AUTO" -> {
                ContratAssuranceAuto a = new ContratAssuranceAuto();
                a.setNumImmatriculation(req.numImmatriculation());
                a.setMarqueVehicule(req.marqueVehicule());
                a.setModeleVehicule(req.modeleVehicule());
                yield a;
            }
            case "HABITATION" -> {
                ContratAssuranceHabitation h = new ContratAssuranceHabitation();
                h.setTypeLogement(req.typeLogement());
                h.setAdresseLogement(req.adresseLogement());
                h.setSuperficie(req.superficie());
                yield h;
            }
            case "SANTE" -> {
                ContratAssuranceSante s = new ContratAssuranceSante();
                s.setNiveauCouverture(req.niveauCouverture());
                s.setNbPersonnesCouvertes(req.nbPersonnesCouvertes());
                yield s;
            }
            default -> throw new RuntimeException("Type de contrat inconnu : " + req.typeContrat());
        };

        contrat.setDateSouscription(req.dateSouscription());
        contrat.setStatut(StatutContrat.EN_COURS);
        contrat.setMontantCotisation(req.montantCotisation());
        contrat.setDureeContrat(req.dureeContrat());
        contrat.setTauxCouverture(req.tauxCouverture());
        contrat.setClient(client);

        return contratMapper.toResponse(contratRepository.save(contrat));
    }

    @Override
    @Transactional(readOnly = true)
    public ContratResponse getContrat(Long id) {
        return contratMapper.toResponse(
                contratRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Contrat introuvable : " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponse> getAllContrats() {
        return contratRepository.findAll().stream().map(contratMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponse> getContratsByClient(Long clientId) {
        return contratRepository.findByClientId(clientId).stream().map(contratMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContratResponse> getContratsByStatut(StatutContrat statut) {
        return contratRepository.findByStatut(statut).stream().map(contratMapper::toResponse).toList();
    }

    @Override
    public ContratResponse validerContrat(Long id) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable : " + id));
        contrat.setStatut(StatutContrat.VALIDE);
        contrat.setDateValidation(LocalDate.now());
        return contratMapper.toResponse(contratRepository.save(contrat));
    }

    @Override
    public ContratResponse resilierContrat(Long id) {
        ContratAssurance contrat = contratRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrat introuvable : " + id));
        contrat.setStatut(StatutContrat.RESILIE);
        return contratMapper.toResponse(contratRepository.save(contrat));
    }

    @Override
    public void supprimerContrat(Long id) {
        contratRepository.deleteById(id);
    }
}
