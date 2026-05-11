package ma.houssybadr.assurance.repositories;

import ma.houssybadr.assurance.entities.ContratAssuranceSante;
import ma.houssybadr.assurance.enums.NiveauCouverture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratSanteRepository extends JpaRepository<ContratAssuranceSante, Long> {

    List<ContratAssuranceSante> findByNiveauCouverture(NiveauCouverture niveauCouverture);
}
