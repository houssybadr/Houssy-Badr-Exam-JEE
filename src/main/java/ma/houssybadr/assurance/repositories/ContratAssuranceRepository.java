package ma.houssybadr.assurance.repositories;

import ma.houssybadr.assurance.entities.ContratAssurance;
import ma.houssybadr.assurance.enums.StatutContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratAssuranceRepository extends JpaRepository<ContratAssurance, Long> {

    List<ContratAssurance> findByClientId(Long clientId);

    List<ContratAssurance> findByStatut(StatutContrat statut);

    List<ContratAssurance> findByClientIdAndStatut(Long clientId, StatutContrat statut);

    @Query("SELECT c FROM ContratAssurance c LEFT JOIN FETCH c.paiements WHERE c.id = :id")
    java.util.Optional<ContratAssurance> findByIdWithPaiements(Long id);

    @Query("SELECT COUNT(c) FROM ContratAssurance c WHERE c.client.id = :clientId")
    long countByClientId(Long clientId);
}
