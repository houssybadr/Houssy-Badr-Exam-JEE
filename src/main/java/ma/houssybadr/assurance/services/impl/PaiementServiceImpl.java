package ma.houssybadr.assurance.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.houssybadr.assurance.dtos.PaiementRequest;
import ma.houssybadr.assurance.dtos.PaiementResponse;
import ma.houssybadr.assurance.entities.ContratAssurance;
import ma.houssybadr.assurance.entities.Paiement;
import ma.houssybadr.assurance.mappers.PaiementMapper;
import ma.houssybadr.assurance.repositories.ContratAssuranceRepository;
import ma.houssybadr.assurance.repositories.PaiementRepository;
import ma.houssybadr.assurance.exceptions.ResourceNotFoundException;
import ma.houssybadr.assurance.services.IPaiementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PaiementServiceImpl implements IPaiementService {

    private final PaiementRepository paiementRepository;
    private final ContratAssuranceRepository contratRepository;
    private final PaiementMapper paiementMapper;

    @Override
    public PaiementResponse enregistrerPaiement(PaiementRequest req) {
        ContratAssurance contrat = contratRepository.findById(req.contratId())
                .orElseThrow(() -> new ResourceNotFoundException("Contrat introuvable : " + req.contratId()));

        Paiement paiement = Paiement.builder()
                .datePaiement(req.datePaiement())
                .montant(req.montant())
                .type(req.type())
                .contrat(contrat)
                .build();

        return paiementMapper.toResponse(paiementRepository.save(paiement));
    }

    @Override
    @Transactional(readOnly = true)
    public PaiementResponse getPaiement(Long id) {
        return paiementMapper.toResponse(
                paiementRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Paiement introuvable : " + id)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponse> getPaiementsByContrat(Long contratId) {
        return paiementRepository.findByContratId(contratId).stream()
                .map(paiementMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaiementResponse> getPaiementsByClient(Long clientId) {
        return paiementRepository.findByClientId(clientId).stream()
                .map(paiementMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getTotalPaiementsContrat(Long contratId) {
        Double total = paiementRepository.sumMontantByContratId(contratId);
        return total != null ? total : 0.0;
    }

    @Override
    public void supprimerPaiement(Long id) {
        paiementRepository.deleteById(id);
    }
}
