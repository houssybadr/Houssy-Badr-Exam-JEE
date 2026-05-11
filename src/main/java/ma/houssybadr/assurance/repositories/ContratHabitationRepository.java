package ma.houssybadr.assurance.repositories;

import ma.houssybadr.assurance.entities.ContratAssuranceHabitation;
import ma.houssybadr.assurance.enums.TypeLogement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContratHabitationRepository extends JpaRepository<ContratAssuranceHabitation, Long> {

    List<ContratAssuranceHabitation> findByTypeLogement(TypeLogement typeLogement);
}
