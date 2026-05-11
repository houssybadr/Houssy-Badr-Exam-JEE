package ma.houssybadr.assurance.repositories;

import ma.houssybadr.assurance.entities.Paiement;
import ma.houssybadr.assurance.enums.TypePaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {

    List<Paiement> findByContratId(Long contratId);

    List<Paiement> findByType(TypePaiement type);

    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.contrat.id = :contratId")
    Double sumMontantByContratId(Long contratId);

    @Query("SELECT p FROM Paiement p JOIN FETCH p.contrat WHERE p.contrat.client.id = :clientId")
    List<Paiement> findByClientId(Long clientId);
}
