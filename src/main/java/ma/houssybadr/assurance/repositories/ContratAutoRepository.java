package ma.houssybadr.assurance.repositories;

import ma.houssybadr.assurance.entities.ContratAssuranceAuto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContratAutoRepository extends JpaRepository<ContratAssuranceAuto, Long> {

    Optional<ContratAssuranceAuto> findByNumImmatriculation(String numImmatriculation);

    List<ContratAssuranceAuto> findByMarqueVehiculeIgnoreCase(String marque);
}
